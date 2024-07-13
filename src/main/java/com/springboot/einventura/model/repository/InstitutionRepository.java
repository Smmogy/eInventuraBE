package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution,Integer> {
}
