package com.springboot.einventura.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.einventura.model.DTO.ArtiklDTO;
import com.springboot.einventura.model.DTO.ArtiklInventuraDTO;
import com.springboot.einventura.model.DTO.ProstorijaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artikl")
public class Artikl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artikl")
    private Integer idArtikl;

    @Column(name = "name")
    private String name;

    @Column(name = "otpisan")
    private Boolean otpisan = false;

    @Column(name = "datum_kreiranja")
    private Date datumKreiranja;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_prostorija")
    private Prostorija prostorija;

    public ArtiklDTO ToDTO() {
        return new ArtiklDTO(
                idArtikl,
                name,
                prostorija.getIdProstorija(),
                datumKreiranja
        );
    }

    public ArtiklInventuraDTO ToInventuraDTO(boolean prisutan) {
        return new ArtiklInventuraDTO(idArtikl, name, prisutan);
    }
}
