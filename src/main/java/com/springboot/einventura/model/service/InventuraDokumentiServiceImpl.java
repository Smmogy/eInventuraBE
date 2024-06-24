package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.InventuraDokumentiRepository;
import com.springboot.einventura.model.bean.InventuraDokumenti;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InventuraDokumentiServiceImpl implements InventuraDokumentiService{

    private InventuraDokumentiRepository inventuraDokumentiRepository;

    @Autowired
    public InventuraDokumentiServiceImpl(InventuraDokumentiRepository theInventuraDokumentiRepository) {
        inventuraDokumentiRepository = theInventuraDokumentiRepository;

    }

    @Override
    public List<InventuraDokumenti> findAll() {
        return inventuraDokumentiRepository.findAll();
    }

    @Override
    public Optional<InventuraDokumenti> findById(int theId) {
        return inventuraDokumentiRepository.findById(theId);
    }

    @Transactional
    @Override
    public InventuraDokumenti save(InventuraDokumenti theInventuraDokumenti) {
        return inventuraDokumentiRepository.save(theInventuraDokumenti);
    }

    @Override
    public void deleteById(int theId) {
        inventuraDokumentiRepository.deleteById(theId);
    }
}
