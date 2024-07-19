package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.bean.Prostorija;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface InstitutionRepository extends JpaRepository<Institution,Integer> {


}
