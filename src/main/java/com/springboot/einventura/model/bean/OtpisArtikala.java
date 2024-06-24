package com.springboot.einventura.model.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="otpis_artikala")
public class OtpisArtikala {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="id_otpis")
            private Integer idOtips;

            @Column(name="datum_otpisa")
            private Date datumOtpisa;

            @Column(name="razlog_otpisa")
            private String razlogOtpisa;

            @Column(name="kolicina")
            private Integer kolicina;

            @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
            @JoinColumn(name="id_artikl")
            private Artikl artikl;

            @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
            @JoinColumn(name="id_djelatnika_otpisa")
             private Djelatnici djelatnici;

}
