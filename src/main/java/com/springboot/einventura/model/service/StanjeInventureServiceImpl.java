package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.StanjeInventureRepository;
import com.springboot.einventura.model.bean.StanjeInventure;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StanjeInventureServiceImpl implements StanjeInventureService {

    private StanjeInventureRepository stanjeInventureRepository;

    @Autowired
    private StanjeInventureServiceImpl(StanjeInventureRepository theStanjeInventureRepository){
        stanjeInventureRepository = theStanjeInventureRepository;
    }


    @Override
    public List<StanjeInventure> findAll() {
        return stanjeInventureRepository.findAll();
    }

    @Override
    public Optional<StanjeInventure> findById(int theId) {
        return stanjeInventureRepository.findById(theId);
    }

    @Override
    public StanjeInventure save(StanjeInventure theStanjeInventure) {
        return stanjeInventureRepository.save(theStanjeInventure);
    }

    @Override
    public void deleteById(int theId) {

    }
}
