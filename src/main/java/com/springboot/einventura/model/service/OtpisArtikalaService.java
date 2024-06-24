package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.OtpisArtikala;

import java.util.List;
import java.util.Optional;

public interface OtpisArtikalaService {

    List<OtpisArtikala> findAll();

    Optional<OtpisArtikala> findById(int theId);

    OtpisArtikala save(OtpisArtikala theOtpisArtikala);

    void deleteById(int theId);
}
