package com.springboot.einventura.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.einventura.model.DTO.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    @OneToMany
    @JoinColumn(name = "id_prostorija")
    private List<Artikl> artikli = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id_prostorija")
    private List<Artikl> artikls;

    public ProstorijaDTO ToDTO() {
        return new ProstorijaDTO(idProstorija, name, institution.getIdInstitution());
    }

    public ProstorijaInstitucijaDTO toProstorijaInstitucijaDTO() {
        return ProstorijaInstitucijaDTO.builder()
                .idProstorija(this.idProstorija)
                .idInstitution(this.institution != null ? this.institution.getIdInstitution() : null)
                .build();
    }

}