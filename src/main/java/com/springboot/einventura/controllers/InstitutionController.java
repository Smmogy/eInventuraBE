package com.springboot.einventura.controllers;

import com.springboot.einventura.model.DTO.InstitutionDTO;
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
    public List<InstitutionDTO> findAll() {
        return institutionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDTO> findByDTOId(@PathVariable Integer id) {
        return institutionService.findByDTOId(id)
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

    @PutMapping("/{id}")
    public Institution update(@RequestBody Institution institution) {
        return institutionService.save(institution);
    }


}
