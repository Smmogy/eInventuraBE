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
@Table(name="artikli")
public class Artikl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artikl")
    private Integer idArtikl;
    @Column(name = "naziv_artikl")
    private String nazivArtikl;
    @Column(name = "mjerna_jedinica")
    private String mjernaJedinica;
    @Column(name = "dobavljac")
    private String dobavljac;

}
