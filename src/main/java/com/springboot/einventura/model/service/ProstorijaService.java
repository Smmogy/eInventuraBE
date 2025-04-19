package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaInstitucijaDTO;
import com.springboot.einventura.model.bean.Prostorija;

import java.util.List;
import java.util.Optional;

public interface ProstorijaService {

    List<ProstorijaDTO> findAll();

    Optional<ProstorijaDTO> findById(Integer theId);

    ProstorijaDTO save(ProstorijaDTO theProstorija);

    void deleteById(Integer theId);

    List<ProstorijaDTO> getRoomsByInstitutionId(Integer idInstitution);

    ProstorijaInstitucijaDTO getInstitutionIdFromProstorijaId(Integer idProstorija);


}
