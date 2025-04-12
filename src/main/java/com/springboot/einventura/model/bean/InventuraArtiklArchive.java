package com.springboot.einventura.model.bean;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventura_artikl_archive")
public class InventuraArtiklArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_inventura")
    private Inventura inventura;

    @ManyToOne
    @JoinColumn(name = "id_artikl")
    private Artikl artikl;

    @ManyToOne
    @JoinColumn(name = "id_prostorija")
    private Prostorija prostorija;

    @Column(name = "prisutan")
    private Boolean pristuan;

}
