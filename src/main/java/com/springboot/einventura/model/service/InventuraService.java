package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.InventuraDTO;
import com.springboot.einventura.model.repository.InventuraRepository;
import com.springboot.einventura.model.bean.Inventura;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface InventuraService {

    List<Inventura> findAll();

    Optional<Inventura> findById(Integer theId);

    Inventura save(InventuraDTO dto);

    Optional<List<Inventura>> findAllByStanje(Integer stanje);

    void deleteById(Integer theId);

    List<Inventura> getInventurasByUserId(Integer userId);

    void addUsersToInventura(Integer inventuraId, List<Integer> userIds);

    List<InventuraDTO> getAllInventuras();
}
