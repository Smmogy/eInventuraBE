package com.springboot.einventura.controllers;

import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.service.InstitutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institution")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Controller
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public List<Institution> findAll() {
        return institutionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Institution> findById(@PathVariable Integer id) {
        return institutionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Institution save(@RequestBody Institution institution) {
        return institutionService.save(institution);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        institutionService.deleteById(id);
    }
}
