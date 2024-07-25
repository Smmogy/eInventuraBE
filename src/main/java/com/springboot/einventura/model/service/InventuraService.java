package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.repository.InventuraRepository;
import com.springboot.einventura.model.bean.Inventura;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface InventuraService {

    List<Inventura> findAll();

    Optional<InventuraDTO> findById(Integer theId);

    Optional<InventuraDetailDTO> findByDetailId(Integer theId);
    Inventura save(InventuraDTO dto);

    void deleteById(Integer theId);

    List<Inventura> getInventurasByUserId(Integer userId);

    List<Inventura> getInventurasByUserIdByStanje(Integer userId);

    void addUsersToInventura(Integer inventuraId, List<Integer> userIds);

    List<InventuraListDTO> getAllInventuras();

    List<InventuraStanjeDTO> getAllInventurasByStanje();

    void updateArticlePresence(int idArtikl, int idInventura, boolean prisutan);

    void zavrsiInventuru(int idInventura);
}
