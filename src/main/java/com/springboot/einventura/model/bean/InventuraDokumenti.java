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
@Table(name = "inventura_dokumenti")
public
class InventuraDokumenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inv_doc")
    private Integer idInventuraDoc;

    @Column(name = "naziv_dokumenta")
    private String nazivDokumenta;

    @Column(name = "dokument")
    private byte[] dokument;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="id_inventura")
    private Inventura inventura;

}