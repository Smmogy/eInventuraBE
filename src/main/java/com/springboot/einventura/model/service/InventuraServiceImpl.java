package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.Roles;
import com.springboot.einventura.model.bean.User;
import com.springboot.einventura.model.repository.InventuraRepository;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.security.config.auth.AuthenticationResponse;
import com.springboot.einventura.security.config.auth.RegisterRequest;
import jakarta.transaction.Transactional;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InventuraServiceImpl implements InventuraService{

    private InventuraRepository inventuraRepository;

    @Autowired
    public InventuraServiceImpl(InventuraRepository theInventuraRepository) {
        inventuraRepository = theInventuraRepository;

    }

    @Override
    public List<Inventura> findAll() {
        return inventuraRepository.findAll();
    }

    @Override
    public Optional<Inventura> findById(int theId) {
        return inventuraRepository.findById(theId);
    }

    @Transactional
    @Override
    public Inventura save(Inventura theInventura) {
        theInventura.setStanje(1);
        return inventuraRepository.save(theInventura);

    }

    @Override
    public void deleteById(int theId) {
        inventuraRepository.deleteById(theId);
    }

    @Override
    public Optional<List<Inventura>> findAllByStanje(int stanje){
        return inventuraRepository.findByStanje(stanje);
    }


}
