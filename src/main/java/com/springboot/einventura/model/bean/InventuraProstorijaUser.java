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
@Table(
        name = "inventuraProstorijaUser",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"id_inventura", "id_prostorija", "id_user"})
)
public class InventuraProstorijaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_inventura")
    private Inventura inventura;

    @ManyToOne
    @JoinColumn(name = "id_prostorija")
    private Prostorija prostorija;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
