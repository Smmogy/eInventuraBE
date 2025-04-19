package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.ArtiklDTO;
import com.springboot.einventura.model.bean.Artikl;

import java.util.List;
import java.util.Optional;

public interface ArtiklService {

    List<ArtiklDTO> findAll();

    Optional<ArtiklDTO> findById(Integer theId);

    List<ArtiklDTO> findByProstorijaIdNeotpisani(Integer idProstorija);

    ArtiklDTO save(ArtiklDTO dto);

    void deleteById(Integer theId);

    void premjestiArtikl(Integer artiklId, Integer novaProstorijaId);

    ArtiklDTO findProstorijaDTOByArtiklId(Integer idArtikl);

}
