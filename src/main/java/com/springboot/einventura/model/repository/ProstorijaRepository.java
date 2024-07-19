package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProstorijaRepository extends JpaRepository<Prostorija, Integer> {
}
