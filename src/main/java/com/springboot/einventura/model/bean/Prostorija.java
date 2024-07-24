package com.springboot.einventura.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaDetailDTO;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_institution")
    private Institution institution;

    @OneToMany
    @JoinColumn(name = "id_prostorija")
    private List<Artikl> artikls;

    public ProstorijaDTO ToDTO() {
        return new ProstorijaDTO(idProstorija, name, institution.getIdInstitution());
    }

    public ProstorijaDetailDTO ToDetailDTO(List<Integer> prisutniAritkli) {
        return new ProstorijaDetailDTO(
                idProstorija,
                name,
                artikls.stream()
                        .map((Artikl a) -> a.ToInventuraDTO(prisutniAritkli.contains(a.getIdArtikl())))
                        .toList()
        );
    }
}