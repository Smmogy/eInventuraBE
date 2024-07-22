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
public class InstitutionDTO {
    private Integer idInstitution;
    private String name;
    private List<Integer> inventurasIds;
    private List<Integer> prostorijasIds;
}
