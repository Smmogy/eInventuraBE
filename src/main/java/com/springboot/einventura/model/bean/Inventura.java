package com.springboot.einventura.model.bean;

import com.springboot.einventura.model.DTO.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventura")
public class Inventura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventura")
    private Integer idInventura;

    @Column(name = "naziv")
    @NotNull
    private String naziv;

    @Column(name = "datum_pocetka")
    @NotNull
    private String datumPocetka;

    @Column(name = "datum_zavrsetka")
    @NotNull
    private String datumZavrsetka;

    @Column(name = "akademska_godina")
    @NotNull
    private Integer akademskaGod;

    @Column(name = "stanje")
    private Boolean stanje;

    @ManyToOne
    @JoinColumn(name = "id_institution")
    @NotNull
    private Institution institution;

    @ManyToMany
    @JoinTable(
            name = "inventura_prisutan_artikl",
            joinColumns = @JoinColumn(name = "inventura_id"),
            inverseJoinColumns = @JoinColumn(name = "artikl_id")
    )
    private List<Artikl> prisutniArtikli;

    @OneToMany(mappedBy = "inventura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventuraArtiklArchive> artiklArchives;

    @OneToMany(mappedBy = "inventura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventuraProstorijaUser> inventuraProstorijaUsers = new ArrayList<>();

    public InventuraDTO toDTO() {
        var roomUserMap = inventuraProstorijaUsers.stream()
                .collect(Collectors.groupingBy(InventuraProstorijaUser::getProstorija))
                .entrySet().stream().collect(Collectors.toMap(
                        e -> e.getKey().getIdProstorija(),
                        e -> e.getValue().stream().map(o -> o.getUser().toDTO()).collect(Collectors.toList())
                ));

        return new InventuraDTO(
                idInventura,
                naziv,
                datumPocetka,
                datumZavrsetka,
                akademskaGod,
                institution.getIdInstitution(),
                stanje,
                roomUserMap
        );
    }

    public InventuraListDTO toListDTO() {
        return new InventuraListDTO(
                idInventura,
                naziv,
                datumPocetka,
                datumZavrsetka,
                akademskaGod,
                institution.getName(),
                stanje
        );

    }

    public InventuraStanjeDTO toStanjeDTO() {
        return new InventuraStanjeDTO(
                idInventura,
                naziv,
                datumPocetka,
                datumZavrsetka,
                akademskaGod,
                institution.getName(),
                stanje
        );

    }

}