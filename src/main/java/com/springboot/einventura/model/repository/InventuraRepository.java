package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Inventura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventuraRepository extends JpaRepository<Inventura, Integer> {
    Optional<List<Inventura>> findByStanje(Integer stanje);
}
