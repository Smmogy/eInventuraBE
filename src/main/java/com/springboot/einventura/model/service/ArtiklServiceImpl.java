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

import java.util.List;
import java.util.Optional;
@Service
public class ArtiklServiceImpl implements ArtiklService {
    @Autowired
    private ArtiklRepository artiklRepository;
    @Autowired
    private ProstorijaRepository prostorijaRepository;


    @Override
    public List<Artikl> findAll() {
        return artiklRepository.findAll();
    }

    @Override
    public Optional<Artikl> findById(Integer theId) {
        return artiklRepository.findById(theId);
    }

    @Override
    public List<Artikl> findByProstorijaIdNeotpisani(Integer idProstorija) {
        return artiklRepository.findByProstorijaIdProstorijaAndOtpisanFalse(idProstorija);
    }

    @Override
    public Artikl save(ArtiklDTO dto) {
        Artikl model;

        if (dto.getIdArtikl() == 0) {
            model = new Artikl();
        }
        else {
            Optional<Artikl> modelOpt = artiklRepository.findById(dto.getIdArtikl());
            if (!modelOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            model = modelOpt.get();
        }

        if (dto.getIdArtikl() == 0) {
            Optional<Prostorija> prostorija = prostorijaRepository.findById(dto.getIdProstorija());
            if (!prostorija.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            model.setProstorija(prostorija.get());
        }

        model.setName(dto.getName());

        return artiklRepository.save(model);
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

