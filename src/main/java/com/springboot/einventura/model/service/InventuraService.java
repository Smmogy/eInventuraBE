package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.InventuraRepository;
import com.springboot.einventura.model.bean.Inventura;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface InventuraService {

    List<Inventura> findAll();

    Optional<Inventura> findById(int theId);

    Inventura save(Inventura theInventura);

    Optional<List<Inventura>> findAllByStanje(int stanje);

    void deleteById(int theId);

}
