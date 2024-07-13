package com.springboot.einventura.model.bean;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "inventura")
public class Inventura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventura")
    private Integer idInventura;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "datum_pocetka")
    private String datumPocetka;

    @Column(name = "datum_zavrsetka")
    private String datumZavrsetka;

    @Column(name = "akademska_godina")
    private Integer akademskaGod;

    @Column(name = "stanje")
    private Long stanje;

    @OneToOne( cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="id_inventura")
    private Institution institution;

}