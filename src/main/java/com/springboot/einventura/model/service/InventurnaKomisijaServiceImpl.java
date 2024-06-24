package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.InventurnaKomisijaRepository;
import com.springboot.einventura.model.bean.InventurnaKomisija;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InventurnaKomisijaServiceImpl implements InventurnaKomisijaService {

    private InventurnaKomisijaRepository inventurnaKomisijaRepository;

    @Autowired
    public InventurnaKomisijaServiceImpl(InventurnaKomisijaRepository theInventurnaKomisijaRepository) {
        inventurnaKomisijaRepository = theInventurnaKomisijaRepository;

    }

    @Override
    public List<InventurnaKomisija> findAll() {
        return inventurnaKomisijaRepository.findAll();
    }

    @Override
    public Optional<InventurnaKomisija> findById(int theId) {
        return inventurnaKomisijaRepository.findById(theId);
    }

    @Transactional
    @Override
    public InventurnaKomisija save(InventurnaKomisija theInventurnaKomisija) {
        return inventurnaKomisijaRepository.save(theInventurnaKomisija);
    }

    @Override
    public void deleteById(int theId) {
        inventurnaKomisijaRepository.deleteById(theId);
    }
}
