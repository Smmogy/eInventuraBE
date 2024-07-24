package com.springboot.einventura.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtiklPrisutanDTO {
    private Integer idArtikl;
    private Integer idInventura;
}
