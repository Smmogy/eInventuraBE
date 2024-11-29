package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.InstitutionDTO;
import com.springboot.einventura.model.bean.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {
    List<InstitutionDTO> findAll();

    Optional<Institution> findById(Integer id);
    Optional<InstitutionDTO> findByDTOId(Integer id);

    Institution save(Institution institution);

    void deleteById(Integer id);
}
