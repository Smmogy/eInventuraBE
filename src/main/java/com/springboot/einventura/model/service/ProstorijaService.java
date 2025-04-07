package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaInstitucijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaUserDTO;
import com.springboot.einventura.model.bean.Prostorija;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProstorijaService {

    List<Prostorija> findAll();

    Optional<Prostorija> findById(Integer theId);

    Prostorija save(ProstorijaDTO theProstorija);

    Prostorija saveUserid(ProstorijaUserDTO theProstorija);

    void deleteById(Integer theId);

   List<Prostorija> getRoomsByInstitutionId(Integer idInstitution);

   ProstorijaInstitucijaDTO getInstitutionIdFromProstorijaId(Integer idProstorija);



}
