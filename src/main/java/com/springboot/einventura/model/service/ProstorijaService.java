package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaInstitucijaDTO;
import com.springboot.einventura.model.bean.Prostorija;

import java.util.List;
import java.util.Optional;

public interface ProstorijaService {

    List<Prostorija> findAll();

    Optional<Prostorija> findById(Integer theId);

    Prostorija save(ProstorijaDTO theProstorija);

    void deleteById(Integer theId);

    List<Prostorija> getRoomsByInstitutionId(Integer idInstitution);

    ProstorijaInstitucijaDTO getInstitutionIdFromProstorijaId(Integer idProstorija);


}
