package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.ArtiklRepository;
import com.springboot.einventura.model.bean.Artikl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ArtiklServiceImpl implements ArtiklService {
    private ArtiklRepository artiklRepository;

    @Autowired
    public ArtiklServiceImpl(ArtiklRepository theArtiklRepository) {
        artiklRepository = theArtiklRepository;

    }

    @Override
    public List<Artikl> findAll() {
        return artiklRepository.findAll();
    }

    @Override
    public Optional<Artikl> findById(int theId) {
        return artiklRepository.findById(theId);
    }

    @Transactional
    @Override
    public Artikl save(Artikl theArtikl) {
        return artiklRepository.save(theArtikl);
    }

    @Override
    public void deleteById(int theId) {
        artiklRepository.deleteById(theId);
    }

}

