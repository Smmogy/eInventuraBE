package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.InventuraProstorijaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventuraProstorijaUserRepository extends JpaRepository<InventuraProstorijaUser, Integer> {
    public List<InventuraProstorijaUser> findByUserId(Integer id);
}
