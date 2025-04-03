package com.springboot.einventura.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.einventura.model.DTO.ProstorijaArtiklDTO;
import com.springboot.einventura.model.DTO.ProstorijaDTO;
import com.springboot.einventura.model.DTO.ProstorijaDetailDTO;
import com.springboot.einventura.model.DTO.ProstorijaUserDTO;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_institution")
    private Institution institution;

    @ManyToMany
    @JoinTable(
            name = "prostorija_user",
            joinColumns = @JoinColumn(name = "id_prostorija"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id_prostorija")
    private List<Artikl> artikli = new ArrayList<>();

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
                        .filter((Artikl a) -> !a.getOtpisan())
                        .map((Artikl a) -> a.ToInventuraDTO(prisutniAritkli.contains(a.getIdArtikl())))
                        .toList()
        );
    }

    public ProstorijaArtiklDTO ToArtiklDTO(List<Integer> prisutniAritkli) {
        return new ProstorijaArtiklDTO(
                name,
                artikls.stream()
                        .map(Artikl::getIdArtikl)
                        .toList()
        );

    }

    public ProstorijaUserDTO toUserDTO(List<Integer> prisutniUser) {
        return new ProstorijaUserDTO(
                idProstorija,
                name,
                users.stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
        );
    }
}