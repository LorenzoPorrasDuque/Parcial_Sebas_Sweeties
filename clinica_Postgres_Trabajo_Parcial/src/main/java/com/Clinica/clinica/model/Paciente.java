package com.Clinica.clinica.model;

import com.Clinica.clinica.dto.OdontologoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class Paciente implements Serializable , Comparable<Paciente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( unique = true, nullable = false)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String DNI;

    @Column
    private Date fechaDeAlta;

    @Override
    public int compareTo(Paciente o) {
        return this.nombre.compareToIgnoreCase(o.getNombre());
    }

    //JoinColumn es una relacion unidireccional mappedBy Es bidireccional las dos no se pueden manejar al tiempo

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Turno> turnos = new HashSet<>();

}
