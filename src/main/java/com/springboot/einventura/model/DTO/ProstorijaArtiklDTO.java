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
    public class ProstorijaArtiklDTO {
        private String name;
        private List<Integer> idArtikls;

    }



