package com.springboot.einventura.controllers;

import com.springboot.einventura.model.bean.Prostorija;
import com.springboot.einventura.model.service.ProstorijaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ProstorijaController {

    private final ProstorijaService prostorijaService;

    @Autowired
    public ProstorijaController(ProstorijaService prostorijaService) {
        this.prostorijaService = prostorijaService;
    }

    @GetMapping
    public List<Prostorija> findAll() {
        return prostorijaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prostorija> findById(@PathVariable Integer id) {
        return prostorijaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prostorija save(@RequestBody Prostorija prostorija) {
        return prostorijaService.save(prostorija);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        prostorijaService.deleteById(id);
    }
}
