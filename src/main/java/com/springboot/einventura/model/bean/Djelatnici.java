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
@Table(name = "djelatnici")
public
class Djelatnici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_djelatnik")
    private Integer idDjelatnik;

    @Column(name = "ime_prezime")
    private String imePrezime;

    @Column(name = "telefon")
    private String telefon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User id;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "raspored_inventura_djelatnici",
            joinColumns = @JoinColumn(name = "djelatnici"),
            inverseJoinColumns = @JoinColumn(name = "inventura")
    )
    private List<Inventura> inventuraList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "raspored_inventura_djelatnici",
            joinColumns = @JoinColumn(name = "djelatnici"),
            inverseJoinColumns = @JoinColumn(name = "raspored_inventura")
    )
    private List<RasporedInventura> rasporedInventuraList;


}

