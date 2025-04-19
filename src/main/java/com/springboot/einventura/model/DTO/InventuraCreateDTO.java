package com.springboot.einventura.model.DTO;

import com.springboot.einventura.model.bean.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventuraCreateDTO {
    private Integer idInventura;
    private String naziv;
    private String datumPocetka;
    private String datumZavrsetka;
    private Integer akademskaGod;
    private Integer institutionId;
    private Boolean stanje;
    private Map<Integer, List<User>> roomUserMap;
}
