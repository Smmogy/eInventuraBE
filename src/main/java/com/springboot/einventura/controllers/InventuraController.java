package com.springboot.einventura.controllers;

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
    private final InventuraService inventuraService;

    public InventuraController(InventuraService inventuraService) {this.inventuraService = inventuraService;}

    @GetMapping("findAll")
    public ResponseEntity<List<Inventura>> getAllInventura(){
        try
        {
            return ResponseEntity.ok(inventuraService.findAll());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("saveInventura")
    public <T> ResponseEntity<T> saveInventura(@RequestBody Inventura inventura){

        Inventura fetchedInventura = inventuraService.save(inventura);
        if(fetchedInventura == null)
                return ResponseEntity.badRequest().body((T) "Neuspjesno spremanje");
        return ResponseEntity.ok((T) "Uspjesno spremanje inventure");

    }
}
