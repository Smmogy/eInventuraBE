package com.springboot.einventura.model.bean;

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
@Table(name = "inventurna_komisija")
public class InventurnaKomisija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventurna_komisija")
    private Integer idKomisija;

    @Column(name="uloga")
    private String uloga;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name="id_djelatnik")
    private Djelatnici djelatnici;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="id_inventura")
    private Inventura inventura;

}
