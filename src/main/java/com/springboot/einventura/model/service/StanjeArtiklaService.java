package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.StanjeArtikla;

import java.util.List;
import java.util.Optional;

public interface StanjeArtiklaService {

    List<StanjeArtikla> findAll();

    Optional<StanjeArtikla> findById(int theId);

    StanjeArtikla save(StanjeArtikla theStanjeArtikla);

    void deleteById(int theId);
}
