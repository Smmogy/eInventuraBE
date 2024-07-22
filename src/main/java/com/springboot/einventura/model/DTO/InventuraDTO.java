package com.springboot.einventura.model.DTO;

import com.springboot.einventura.model.bean.Djelatnici;
import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.bean.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventuraDTO {
    private Integer idInventura;
    private String naziv;
    private String datumPocetka;
    private String datumZavrsetka;
    private Integer akademskaGod;
    private List<Integer> usersIds;
    private Integer institutionId;
}
