package com.springboot.einventura.controllers;

import com.springboot.einventura.model.bean.Artikl;
import com.springboot.einventura.model.service.ArtiklService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms/artikl")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Controller
public class ArtiklController {
    @Autowired
    private ArtiklService artiklService;

    @GetMapping
    public List<Artikl> findAll() {
        return artiklService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artikl> findById(@PathVariable Integer id) {
        return artiklService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Artikl save(@RequestBody Artikl artikl) {
        return artiklService.save(artikl);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        artiklService.deleteById(id);
    }
}