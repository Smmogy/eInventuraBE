package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService{
    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Optional<Institution> findById(Integer id) {
        return institutionRepository.findById(id);
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
