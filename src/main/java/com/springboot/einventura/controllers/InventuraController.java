package com.springboot.einventura.controllers;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.bean.Prostorija;
import com.springboot.einventura.model.service.InventuraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventura")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Controller
public class InventuraController {

    @Autowired
    private InventuraService inventuraService;

    @GetMapping
    public List<InventuraListDTO> getAllInventuras() {
        return inventuraService.getAllInventuras();
    }

    @GetMapping("/stanje")
    public List<InventuraStanjeDTO> getAllInventurasByStanje() {
        return inventuraService.getAllInventurasByStanje();
    }

    @PostMapping
    public InventuraDTO save(@RequestBody InventuraDTO inventuraDTO) {
        return inventuraService.save(inventuraDTO).toDTO();
    }
    @GetMapping("/user/{userId}")
    public List<InventuraListDTO> getInventurasByUserId(@PathVariable Integer userId) {
        return inventuraService.getInventurasByUserId(userId).stream().map(Inventura::toListDTO).toList();
    }
    @GetMapping("/stanje/user/{userId}")
    public List<InventuraStanjeDTO> getInventurasByUserIdByStanje(@PathVariable Integer userId) {
        return inventuraService.getInventurasByUserIdByStanje(userId).stream().map(Inventura::toStanjeDTO).toList();
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
        inventuraService.   deleteById(id);
    }

    @PostMapping("/update-article-presence")
    public ResponseEntity updateArticlePresence(@RequestBody ArtiklPrisutanDTO artiklPrisutanDTO) {
        inventuraService.updateArticlePresence(artiklPrisutanDTO.getIdArtikl(), artiklPrisutanDTO.getIdInventura(), artiklPrisutanDTO.getPrisutan());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/zavrsi/{idInventura}")
    public ResponseEntity zavrsiInventuru(@PathVariable int idInventura) {
        try {
            inventuraService.zavrsiInventuru(idInventura);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
