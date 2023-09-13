package com.Clinica.clinica.model;

import com.Clinica.clinica.dto.OdontologoDTO;
import com.Clinica.clinica.dto.PacienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turnos")
public class Turno implements Serializable, Comparable<Turno> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( unique = true , nullable = false )
    private Long id;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaHora;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;


    @Override
    public int compareTo(Turno o) {return this.fechaHora.compareTo(o.getFechaHora());}

}
