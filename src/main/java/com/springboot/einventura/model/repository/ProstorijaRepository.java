package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProstorijaRepository extends JpaRepository<Prostorija, Integer> {
}
