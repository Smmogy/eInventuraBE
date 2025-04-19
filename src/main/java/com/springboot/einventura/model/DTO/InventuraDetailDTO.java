package com.springboot.einventura.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventuraDetailDTO {
    private Integer idInventura;
    private String naziv;
    private String datumPocetka;
    private String datumZavrsetka;
    private Integer akademskaGod;
    private Boolean stanje;
    private InstitutionDTO institution;
    private List<ProstorijaDTO> prostorije;
}
