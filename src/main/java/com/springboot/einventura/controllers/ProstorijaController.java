package com.springboot.einventura.controllers;

import com.springboot.einventura.model.DTO.InstitutionDTO;
import com.springboot.einventura.model.DTO.InventuraDTO;
import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaUserDTO;
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
    @PostMapping("/save")
    public ResponseEntity<ProstorijaUserDTO> saveProstorija(@RequestBody ProstorijaUserDTO prostorijaUserDTO) {
        // Call the service method to save Prostorija with the users
        Prostorija savedProstorija = prostorijaService.saveUserid(prostorijaUserDTO);

        // Extract the user IDs from savedProstorija's users list
        List<Integer> userIds = savedProstorija.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());

        // Now pass the extracted user IDs to the toUserDTO method
        ProstorijaUserDTO savedProstorijaDTO = savedProstorija.toUserDTO(userIds);

        // Return the saved ProstorijaUserDTO in the response
        return ResponseEntity.ok(savedProstorijaDTO);
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

}
