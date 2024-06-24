package com.springboot.einventura.model.service;

import com.springboot.einventura.model.repository.DjelatniciRepository;
import com.springboot.einventura.model.bean.Djelatnici;
import java.util.List;
import java.util.Optional;

public interface DjelatniciService {

    List<Djelatnici> findAll();

    Optional<Djelatnici> findById(int theId);

    Djelatnici save(Djelatnici theDjejatnic);

    void deleteById(int theId);

}
