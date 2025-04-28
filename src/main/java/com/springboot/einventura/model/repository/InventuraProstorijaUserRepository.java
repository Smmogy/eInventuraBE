package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.InventuraProstorijaUser;
import com.springboot.einventura.model.bean.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventuraProstorijaUserRepository extends JpaRepository<InventuraProstorijaUser, Integer> {
    public List<InventuraProstorijaUser> findByUserId(Integer id);

    public List<InventuraProstorijaUser> findByInventuraIdInventuraAndProstorijaIdProstorija(Integer idInventura, Integer idProstorija);

    public List<InventuraProstorijaUser> findByInventuraIdInventuraAndUserId(Integer idInventura, Integer id);

}
