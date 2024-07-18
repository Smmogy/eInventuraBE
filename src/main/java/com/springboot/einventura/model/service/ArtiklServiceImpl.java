package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.ArtiklRepository;
import com.springboot.einventura.model.bean.Artikl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
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
    public Optional<Artikl> findById(Integer theId) {
        return artiklRepository.findById(theId);
    }

    @Override
    public Artikl save(Artikl theArtikl) {
        return artiklRepository.save(theArtikl);
    }

    @Override
    public void deleteById(Integer theId) {
        artiklRepository.deleteById(theId);
    }


}

