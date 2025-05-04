package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ArtiklDTO;
import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.bean.Prostorija;
import com.springboot.einventura.model.repository.ArtiklRepository;
import com.springboot.einventura.model.bean.Artikl;
import com.springboot.einventura.model.repository.ProstorijaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArtiklServiceImpl implements ArtiklService {
    @Autowired
    private ArtiklRepository artiklRepository;
    @Autowired
    private ProstorijaRepository prostorijaRepository;


    @Override
    public List<ArtiklDTO> findAll() {
        return artiklRepository.findAll().stream().map(Artikl::ToDTO).toList();
    }

    @Override
    public Optional<ArtiklDTO> findById(Integer theId) {
        return artiklRepository.findById(theId).map(Artikl::ToDTO);
    }

    @Override
    public List<ArtiklDTO> findByProstorijaIdNeotpisani(Integer idProstorija) {
        return artiklRepository.findByProstorijaIdProstorijaAndOtpisanFalse(idProstorija).stream().map(Artikl::ToDTO).toList();
    }

    @Override
    public ArtiklDTO save(ArtiklDTO dto) {
        Artikl model;

        if (dto.getIdArtikl() == null || dto.getIdArtikl() == 0) {
            // Novi artikl
            model = new Artikl();
            model.setDatumKreiranja(new Date()); // Postavljanje trenutnog vremena
        } else {
            // Ažuriranje postojećeg artikla
            Optional<Artikl> existingOpt = artiklRepository.findById(dto.getIdArtikl());
            if (!existingOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artikl nije pronađen.");
            }
            model = existingOpt.get();
            // Zadržavanje postojećeg datuma kreiranja
        }

        // Postavljanje ili ažuriranje ostalih polja
        model.setName(dto.getName());

        Optional<Prostorija> prostorijaOpt = prostorijaRepository.findById(dto.getIdProstorija());
        if (!prostorijaOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prostorija nije pronađena.");
        }
        model.setProstorija(prostorijaOpt.get());

        return artiklRepository.save(model).ToDTO();
    }


    @Override
    public void deleteById(Integer theId) {
        artiklRepository.deleteById(theId);
    }

    @Override
    public void premjestiArtikl(Integer artiklId, Integer novaProstorijaId) {
        Artikl artikl = artiklRepository.findById(artiklId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artikl ne postoji"));

        Prostorija novaProstorija = prostorijaRepository.findById(novaProstorijaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prostorija ne postoji"));

        artikl.setProstorija(novaProstorija);
        artiklRepository.save(artikl);
    }

}

