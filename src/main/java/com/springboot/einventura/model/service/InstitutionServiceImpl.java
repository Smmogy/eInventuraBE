package com.springboot.einventura.model.service;

import com.springboot.einventura.model.DTO.InstitutionDTO;
import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.repository.InstitutionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService{
    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public List<InstitutionDTO> findAll() {
        return institutionRepository.findAll().stream().map(Institution::toDTO).toList();
    }

    @Override
    @Transactional
    public Optional<Institution> findById(Integer id) {
        return institutionRepository.findById(id);
    }
    @Override
    @Transactional
    public Optional<InstitutionDTO> findByDTOId(Integer id) {
        return institutionRepository.findById(id).map(Institution::toDTO);
    }

    @Override
    public Institution save(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Override
    public void deleteById(Integer id) {
       institutionRepository.deleteById(id);
    }
}
