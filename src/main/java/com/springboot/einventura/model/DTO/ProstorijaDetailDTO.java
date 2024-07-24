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
public class ProstorijaDetailDTO {
    private Integer idProstorija;
    private String name;
    private List<ArtiklDTO> artiklDTO;
}
