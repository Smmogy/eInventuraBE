package com.springboot.einventura.model.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prostorija")
public class Prostorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prostorija")
    private Integer idProstorija;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_institution")
    private Institution institution;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_prostorija")
    private Set<Artikl> artikls;
}
