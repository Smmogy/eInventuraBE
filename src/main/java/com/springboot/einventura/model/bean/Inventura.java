package com.springboot.einventura.model.bean;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventura")
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
    private Integer stanje;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "raspored_inventura_djelatnici",
            joinColumns = @JoinColumn(name = "inventura"),
            inverseJoinColumns = @JoinColumn(name = "djelatnici")
    )
    private List<Djelatnici> djelatniciList;


}