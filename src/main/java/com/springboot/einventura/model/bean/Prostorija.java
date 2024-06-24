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
@Table(name = "prostorija")
public class Prostorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_prostorija")
    private Integer idProstorija;
    @Column(name ="naziv_prostorije")
    private String nazivProstorije;
    @Column(name ="opis")
    private String opis;

}
