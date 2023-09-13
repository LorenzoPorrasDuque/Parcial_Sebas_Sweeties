package com.Clinica.clinica.dto;

import com.Clinica.clinica.model.Domicilio;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.model.Turno;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private Set<Paciente> pacientes = new HashSet<>();

    private Turno turno;

    private Domicilio domicilio;

}
