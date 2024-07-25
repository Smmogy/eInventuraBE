package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Inventura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventuraRepository extends JpaRepository<Inventura, Integer> {
    List<Inventura> findByStanjeTrue();
}
