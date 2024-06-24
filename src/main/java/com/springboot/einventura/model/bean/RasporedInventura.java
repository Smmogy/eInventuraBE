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
@Entity
@Table(name = "raspored_inventura")
public class RasporedInventura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_raspored")
    private Integer idRaspored;

    @Column(name ="datum_pocetka")
    private Date datumPocetka;

    @Column(name ="datum_zavrsetka")
    private Date datumZavrsetka;

    @Column(name ="opis")
    private String opis;

    @Column(name ="status")
    private Integer status;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name ="id_inventura")
    private Inventura inventura;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "raspored_inventura_djelatnici",
            joinColumns = @JoinColumn(name = "raspored_inventura"),
            inverseJoinColumns = @JoinColumn(name = "djelatnici")
    )
    private List<Djelatnici> djelatniciList;

}
