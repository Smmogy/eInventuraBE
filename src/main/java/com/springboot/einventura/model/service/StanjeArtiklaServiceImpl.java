package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.StanjeArtiklaRepository;
import com.springboot.einventura.model.bean.StanjeArtikla;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StanjeArtiklaServiceImpl implements StanjeArtiklaService {

    private StanjeArtiklaRepository stanjeArtiklaRepository;

    @Autowired
    private StanjeArtiklaServiceImpl(StanjeArtiklaRepository theStanjeArtiklaRepository){
        stanjeArtiklaRepository = theStanjeArtiklaRepository;
    }


    @Override
    public List<StanjeArtikla> findAll() {
        return stanjeArtiklaRepository.findAll();
    }

    @Override
    public Optional<StanjeArtikla> findById(int theId) {
        return stanjeArtiklaRepository.findById(theId);
    }

    @Override
    public StanjeArtikla save(StanjeArtikla theStanjeArtikla) {
        return stanjeArtiklaRepository.save(theStanjeArtikla);
    }

    @Override
    public void deleteById(int theId) {

    }
}
