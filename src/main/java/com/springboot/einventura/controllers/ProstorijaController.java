package com.springboot.einventura.controllers;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.Institution;
import com.springboot.einventura.model.bean.Prostorija;
import com.springboot.einventura.model.bean.User;
import com.springboot.einventura.model.service.InstitutionService;
import com.springboot.einventura.model.service.ProstorijaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ProstorijaController {

    @Autowired
    private ProstorijaService prostorijaService;
    @Autowired
    private InstitutionService institutionService;


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
    public Prostorija save(@RequestBody ProstorijaDTO prostorija) {
        return prostorijaService.save(prostorija);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        prostorijaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Prostorija update(@RequestBody ProstorijaDTO prostorija) {
        return prostorijaService.save(prostorija);
    }


    @GetMapping("/institution/{id}")
    public ResponseEntity<List<Prostorija>> getRoomsByInstitutionId(@PathVariable Integer id) {
        Optional<Institution> institution = institutionService.findById(id);
        return institution
                .map(value -> ResponseEntity.ok(value.getProstorijas())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/institution-by-room/{idProstorija}")
    public ResponseEntity<ProstorijaInstitucijaDTO> getInstitutionFromRoom(@PathVariable Integer idProstorija) {
        try {
            ProstorijaInstitucijaDTO dto = prostorijaService.getInstitutionIdFromProstorijaId(idProstorija);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
