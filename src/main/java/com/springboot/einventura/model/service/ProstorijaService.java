package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.Prostorija;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface ProstorijaService {

    List<Prostorija> findAll();

    Optional<Prostorija> findById(Integer theId);

    Prostorija save(Prostorija theProstorija);

    void deleteById(Integer theId);


}
