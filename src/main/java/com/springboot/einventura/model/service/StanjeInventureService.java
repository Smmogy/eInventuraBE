package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.StanjeInventure;

import java.util.List;
import java.util.Optional;

public interface StanjeInventureService {

    List<StanjeInventure> findAll();

    Optional<StanjeInventure> findById(int theId);

    StanjeInventure save(StanjeInventure theStanjeInventure);

    void deleteById(int theId);
}
