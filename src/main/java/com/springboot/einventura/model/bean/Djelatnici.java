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
    @JoinColumn(name = "id_user")
    private User idUser;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "raspored_inventura_djelatnici",
            joinColumns = @JoinColumn(name = "id_djelatnik"),
            inverseJoinColumns = @JoinColumn(name = "id_raspored")
    )
    private List<RasporedInventura> rasporedInventuraList;


}

