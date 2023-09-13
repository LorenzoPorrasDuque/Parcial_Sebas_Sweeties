package com.Clinica.clinica.dto;

import com.Clinica.clinica.model.Domicilio;
import com.Clinica.clinica.model.Odontologo;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO  {

    private Long id;

    private String nombre;

    private String apellido;

    private Odontologo odontologo;

    private Turno turno;

    private Domicilio domicilio;


}
