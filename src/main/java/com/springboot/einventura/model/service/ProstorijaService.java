package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.Prostorija;

import java.util.List;
import java.util.Optional;

public interface ProstorijaService {

    List<Prostorija> findAll();

    Optional<Prostorija> findById(int theId);

    Prostorija save(Prostorija theProstorija);

    void deleteById(int theId);

}
