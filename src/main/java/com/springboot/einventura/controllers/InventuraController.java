package com.springboot.einventura.controllers;

import com.springboot.einventura.model.DTO.*;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.service.InventuraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public InventuraDTO save(@RequestBody InventuraCreateDTO inventuraCreateDTO) {
        return inventuraService.save(inventuraCreateDTO);
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
    public InventuraDetailDTO findByDetailId(@PathVariable Integer id) {
        return inventuraService.findByDetailId(id);
    }

    @GetMapping("/detail/{inventuraId}/prostorija/{prostorijaId}")
    public InventuraDetailProstorijaDTO findByDetailProstorijaId(@PathVariable Integer inventuraId, @PathVariable Integer prostorijaId) {
        return inventuraService.findByDetailProstorijaId(inventuraId, prostorijaId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        inventuraService.deleteById(id);
    }

    @PostMapping("/update-article-presence")
    public ArtiklDTO updateArticlePresence(@RequestBody ArtiklPrisutanDTO artiklPrisutanDTO) {
        ArtiklDTO artikl = inventuraService.updateArticlePresence(artiklPrisutanDTO.getIdArtikl(), artiklPrisutanDTO.getIdInventura(), artiklPrisutanDTO.getPrisutan());
        return artikl;
    }

    @PostMapping("/zavrsi/{idInventura}")
    public ResponseEntity zavrsiInventuru(@PathVariable int idInventura) {
        inventuraService.zavrsiInventuru(idInventura);
        return ResponseEntity.ok().build();
    }
}
