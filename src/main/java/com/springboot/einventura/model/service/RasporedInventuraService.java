package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.RasporedInventura;

import java.util.List;
import java.util.Optional;

public interface RasporedInventuraService {

    List<RasporedInventura> findAll();

    Optional<RasporedInventura> findById(int theId);

    RasporedInventura save(RasporedInventura theRasporedInventura);

    void deleteById(int theId);
}
