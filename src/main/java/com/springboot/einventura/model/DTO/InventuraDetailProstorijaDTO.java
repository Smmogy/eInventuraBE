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
public class InventuraDetailProstorijaDTO {
    private Integer idProstorija;
    private String name;
    private Boolean inventuraStanje;
    private List<ArtiklInventuraDTO> artikls;
    private List<UserProstorijaDTO> users;
}
