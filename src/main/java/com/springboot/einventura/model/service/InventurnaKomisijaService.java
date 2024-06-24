package com.springboot.einventura.model.service;

import com.springboot.einventura.model.bean.InventurnaKomisija;

import java.util.List;
import java.util.Optional;

public interface InventurnaKomisijaService {

    List<InventurnaKomisija> findAll();

    Optional<InventurnaKomisija> findById(int theId);

    InventurnaKomisija save(InventurnaKomisija theInventurnaKomisija);

    void deleteById(int theId);

}
