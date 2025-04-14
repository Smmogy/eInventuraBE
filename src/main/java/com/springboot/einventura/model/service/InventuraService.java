package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.Inventura;

import java.util.List;
import java.util.Optional;

public interface InventuraService {

    List<Inventura> findAll();

    Optional<InventuraDTO> findById(Integer theId);

    Optional<InventuraDetailDTO> findByDetailId(Integer theId);

    ZavrsenaInventuraDTO findByZavsenaId(Integer theId);

    ZavrsenaInventraProstorijaDTO findByProstorijaId(Integer idInventura, Integer idProstorija);

    InventuraDTO save(Probno dto);

    void deleteById(Integer theId);

    List<Inventura> getInventurasByUserId(Integer userId);

    List<Inventura> getInventurasByUserIdByStanje(Integer userId);

    List<InventuraListDTO> getAllInventuras();

    List<InventuraStanjeDTO> getAllInventurasByStanje();

    void updateArticlePresence(int idArtikl, int idInventura, boolean prisutan);

    void zavrsiInventuru(int idInventura);
}
