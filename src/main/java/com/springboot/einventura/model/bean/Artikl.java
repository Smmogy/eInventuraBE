package com.springboot.einventura.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.einventura.model.DTO.ArtiklDTO;
import com.springboot.einventura.model.DTO.ProstorijaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "artikl")
public class Artikl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artikl")
    private Integer idArtikl;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name ="id_prostorija")
    private Prostorija prostorija;

    public ArtiklDTO ToDTO() {
        return new ArtiklDTO(idArtikl, name, prostorija.getIdProstorija());
    }
}
