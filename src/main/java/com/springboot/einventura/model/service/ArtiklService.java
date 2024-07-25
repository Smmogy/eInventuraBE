package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ArtiklDTO;
import com.springboot.einventura.model.bean.Artikl;

import java.util.List;
import java.util.Optional;

public interface ArtiklService {

    List<Artikl> findAll();

    Optional<Artikl> findById(Integer theId);

    List<Artikl> findByProstorijaIdNeotpisani(Integer idProstorija);

    Artikl save(ArtiklDTO dto);

    void deleteById(Integer theId);


}
