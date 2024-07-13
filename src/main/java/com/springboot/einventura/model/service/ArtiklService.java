package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.Artikl;

import java.util.List;
import java.util.Optional;

public interface ArtiklService {

    List<Artikl> findAll();

    Optional<Artikl> findById(Integer theId);

    Artikl save(Artikl theArtikl);

    void deleteById(Integer theId);


}
