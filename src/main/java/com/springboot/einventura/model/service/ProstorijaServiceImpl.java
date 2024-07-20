package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.repository.InstitutionRepository;
import com.springboot.einventura.model.repository.ProstorijaRepository;
import com.springboot.einventura.model.bean.Prostorija;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Service

public class ProstorijaServiceImpl implements ProstorijaService {

    @Autowired
    private ProstorijaRepository prostorijaRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public List<Prostorija> findAll() {
        return prostorijaRepository.findAll();
    }

    @Override
    public Optional<Prostorija> findById(Integer theId) {
        return prostorijaRepository.findById(theId);
    }

    @Transactional
    @Override
    public Prostorija save(ProstorijaDTO dto) {
        Prostorija model;

        if (dto.getIdProstorija() == 0) {
            model = new Prostorija();
        }
        else {
            Optional<Prostorija> modelOpt = prostorijaRepository.findById(dto.getIdProstorija());
            if (!modelOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            model = modelOpt.get();
        }

        if (dto.getIdProstorija() == 0) {
            Optional<Institution> institution = institutionRepository.findById(dto.getIdInstitution());
            if (!institution.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            model.setInstitution(institution.get());
        }

        model.setName(dto.getName());

        return prostorijaRepository.save(model);
    }

    @Override
    public void deleteById(Integer theId) {
        prostorijaRepository.deleteById(theId);
   }

    @Override
    public List<Prostorija> getRoomsByInstitutionId(Integer idInstitution) {
        return null;
   }

}



