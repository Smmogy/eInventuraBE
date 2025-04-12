package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Artikl;
import com.springboot.einventura.model.bean.Inventura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtiklRepository extends JpaRepository<Artikl, Integer> {
   List<Artikl> findByProstorijaInstitutionIdInstitutionAndOtpisanFalse(Integer idInstitution);

   List<Artikl> findByProstorijaIdProstorijaAndOtpisanFalse(Integer idProstorija);

}
