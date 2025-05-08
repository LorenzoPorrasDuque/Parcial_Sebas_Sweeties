package com.Clinica.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "domicilios")
public class Domicilio implements Serializable, Comparable<Domicilio> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String calle;

    @Column
    private String localidad;

    @Column
    private String provincia;

    @JsonIgnore
    @OneToOne(mappedBy = "domicilio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Odontologo odontologo;

    @JsonIgnore
    @OneToOne(mappedBy = "domicilio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Paciente paciente;

    @Override
    public int compareTo(Domicilio o) {
        return this.provincia.compareToIgnoreCase(o.getProvincia());
    }
}
