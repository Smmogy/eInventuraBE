package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.DjelatniciRepository;
import com.springboot.einventura.model.bean.Djelatnici;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DjelatniciServiceImpl implements DjelatniciService {

    private DjelatniciRepository djelatniciRepository;

    @Autowired
    public DjelatniciServiceImpl(DjelatniciRepository theDjelatniciRepository) {
        djelatniciRepository = theDjelatniciRepository;

    }

    @Override
    public List<Djelatnici> findAll() {
        return djelatniciRepository.findAll();
    }

    @Override
    public Optional<Djelatnici> findById(int theId) {
        return djelatniciRepository.findById(theId);
    }

    @Transactional
    @Override
    public Djelatnici save(Djelatnici theDjelatnici) {
        return djelatniciRepository.save(theDjelatnici);
    }

    @Override
    public void deleteById(int theId) {
        djelatniciRepository.deleteById(theId);
    }

}
