package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.RasporedInventuraRepository;
import com.springboot.einventura.model.bean.RasporedInventura;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RasporedInventuraSeriveImpl implements RasporedInventuraService {

    private RasporedInventuraRepository rasporedInventuraRepository;

    @Autowired
    private RasporedInventuraSeriveImpl(RasporedInventuraRepository therasporedInventuraRepository){
        rasporedInventuraRepository = therasporedInventuraRepository;
    }

    @Override
    public List<RasporedInventura> findAll() {
        return rasporedInventuraRepository.findAll();
    }

    @Override
    public Optional<RasporedInventura> findById(int theId) {
        return rasporedInventuraRepository.findById(theId);
    }

    @Override
    public RasporedInventura save(RasporedInventura theRasporedInventura) {
        return rasporedInventuraRepository.save(theRasporedInventura);
    }

    @Override
    public void deleteById(int theId) {

    }
}
