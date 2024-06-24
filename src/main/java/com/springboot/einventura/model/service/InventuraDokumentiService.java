package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.InventuraDokumentiRepository;
import com.springboot.einventura.model.bean.InventuraDokumenti;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface InventuraDokumentiService {

    List<InventuraDokumenti> findAll();

    Optional<InventuraDokumenti> findById(int theId);

    InventuraDokumenti save(InventuraDokumenti theInventuraDokumenti);

    void deleteById(int theId);



}
