package com.springboot.einventura.model.repository;

import com.springboot.einventura.model.bean.Djelatnici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DjelatniciRepository extends JpaRepository<Djelatnici, Integer> {
}
