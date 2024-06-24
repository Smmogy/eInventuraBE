package com.springboot.einventura.model.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stanje_artikla")
public class StanjeArtikla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_stanje")
    private Integer idStanje;

    @Column(name="datum_stanja")
    private Date datumStanja;

    @Column(name = "kolicina")
    private  Integer kolicina;

    @Column(name = "akademska_godina")
    private String akademskaGodina;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "id_artikl")
    private Artikl artikl;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_prostorija")
    private Prostorija prostorija;

}
