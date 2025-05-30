package com.springboot.einventura.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtiklDTO {
    private Integer idArtikl;
    private String name;
    private Integer idProstorija;
    private Date datumKreiranja;
}
