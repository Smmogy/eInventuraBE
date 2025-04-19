package com.springboot.einventura.model.bean;

import com.springboot.einventura.model.DTO.InstitutionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "institution")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institution")
    private Integer idInstitution;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "institution")
    private List<Inventura> inventuras;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.REMOVE)
    private List<Prostorija> prostorijas;

    public InstitutionDTO toDTO() {
        return new InstitutionDTO(
                idInstitution,
                name,
                inventuras.stream()
                        .map(Inventura::getIdInventura)
                        .collect(Collectors.toList()),
                prostorijas.stream()
                        .map(Prostorija::getIdProstorija)
                        .collect(Collectors.toList()));
    }
}