package com.springboot.einventura.model.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stanje_inventure")
public class StanjeInventure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_stanje_inventura")
    private Integer idStanje;

    @Column(name = "evidentirana_kolicina")
    private Integer evidentiranaKolicina;

    @Column(name = "datum_stanje")
    private Date datumStanje;

    @Column(name = "status_stanja")
    private Integer statusStanja;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_inventura")
    private Inventura inventura;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_djelatnik")
    private Djelatnici djelatnici;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_prostorija")
    private List<Prostorija> prostorija;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_stanje")
    private List<Artikl> artikl;

}
