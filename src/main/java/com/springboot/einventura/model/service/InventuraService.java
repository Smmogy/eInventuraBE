package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.Inventura;

import java.util.List;
import java.util.Optional;

public interface InventuraService {

    List<Inventura> findAll();

    Optional<InventuraDTO> findById(Integer theId);

    InventuraDetailDTO findByDetailId(Integer theId, Integer userId);

    InventuraDetailProstorijaDTO findByDetailProstorijaId(Integer idInventura, Integer idProstorija);

    InventuraDTO save(InventuraCreateDTO dto);

    void deleteById(Integer theId);

    List<Inventura> getInventurasByUserId(Integer userId);

    List<Inventura> getInventurasByUserIdByStanje(Integer userId);

    List<InventuraListDTO> getAllInventuras();

    List<InventuraStanjeDTO> getAllInventurasByStanje();

    ArtiklDTO updateArticlePresence(int idArtikl, int idInventura, boolean prisutan);

    void zavrsiInventuru(int idInventura);
}
