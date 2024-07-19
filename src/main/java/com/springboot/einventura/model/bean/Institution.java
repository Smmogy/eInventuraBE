package com.springboot.einventura.model.bean;

import com.springboot.einventura.model.bean.Inventura;
import com.springboot.einventura.model.bean.Prostorija;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "id_inventura")
    private Inventura inventura;

    @OneToMany(mappedBy = "institution")
    private List<Prostorija> prostorijas;

    // Getters and Setters
}

class InstitutionDTO {
    public Integer id;
    public String name;
   // public List<ProstorijaDTO> prostorije;
   // public List<InventuraListDTO> inventure;
}

class InstitutionListDTO {
    public Integer id;
    public String name;
    public Date dateOfLastInventura;
}