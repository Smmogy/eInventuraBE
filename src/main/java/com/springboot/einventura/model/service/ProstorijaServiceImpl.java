package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.ProstorijaRepository;
import com.springboot.einventura.model.bean.Prostorija;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProstorijaServiceImpl implements ProstorijaService {

    private ProstorijaRepository prostorijaRepository;

    @Autowired
    public ProstorijaServiceImpl(ProstorijaRepository theProstorijaRepository) {
        prostorijaRepository = theProstorijaRepository;

    }

    @Override
    public List<Prostorija> findAll() {
        return prostorijaRepository.findAll();
    }

    @Override
    public Optional<Prostorija> findById(int theId) {
        return prostorijaRepository.findById(theId);
    }

    @Transactional
    @Override
    public Prostorija save(Prostorija theProstorija) {
        return prostorijaRepository.save(theProstorija);
    }

    @Override
    public void deleteById(int theId) {
        prostorijaRepository.deleteById(theId);
    }
}
