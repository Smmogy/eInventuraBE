package com.springboot.einventura.controllers;

import com.springboot.einventura.model.DTO.InventuraDTO;
import com.springboot.einventura.model.DTO.InventuraDetailDTO;
import com.springboot.einventura.model.DTO.InventuraListDTO;
import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.bean.Prostorija;
import com.springboot.einventura.model.service.InventuraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/inventura")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Controller
public class InventuraController {

    @Autowired
    private final InventuraService inventuraService;

    public InventuraController(InventuraService inventuraService) {this.inventuraService = inventuraService;}

    @GetMapping
    public List<InventuraDTO> getAllInventuras() {
        return inventuraService.getAllInventuras();
    }

    @PostMapping
    public InventuraDTO save(@RequestBody InventuraDTO inventuraDTO) {
        return inventuraService.save(inventuraDTO).toDTO();
    }
    @GetMapping("/user/{userId}")
    public List<InventuraListDTO> getInventurasByUserId(@PathVariable Integer userId) {
        return inventuraService.getInventurasByUserId(userId).stream().map(Inventura::toListDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventuraDTO> findById(@PathVariable Integer id) {
        return inventuraService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<InventuraDetailDTO> findByDetailId(@PathVariable Integer id) {
        return inventuraService.findByDetailId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        inventuraService.deleteById(id);
    }
}
