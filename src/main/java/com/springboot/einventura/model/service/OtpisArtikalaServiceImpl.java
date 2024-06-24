package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.OtpisArtikalaRepository;
import com.springboot.einventura.model.bean.OtpisArtikala;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OtpisArtikalaServiceImpl implements OtpisArtikalaService {

    private OtpisArtikalaRepository otpisArtikalaRepository;

    @Autowired
    public OtpisArtikalaServiceImpl(OtpisArtikalaRepository theOtpisArtikalaReposritory) {
        otpisArtikalaRepository = theOtpisArtikalaReposritory;

    }

    @Override
    public List<OtpisArtikala> findAll() {
        return otpisArtikalaRepository.findAll();
    }

    @Override
    public Optional<OtpisArtikala> findById(int theId) {
        return otpisArtikalaRepository.findById(theId);
    }

    @Override
    public OtpisArtikala save(OtpisArtikala theOtpisArtikala) {
        return otpisArtikalaRepository.save(theOtpisArtikala);
    }

    @Override
    public void deleteById(int theId) {

    }
}
