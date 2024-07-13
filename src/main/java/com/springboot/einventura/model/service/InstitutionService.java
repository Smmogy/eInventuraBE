package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {
    List<Institution> findAll();

    Optional<Institution> findById(Integer id);

    Institution save(Institution institution);

    void deleteById(Integer id);
}
