package com.Clinica.clinica.dto;


import com.Clinica.clinica.model.Odontologo;
import com.Clinica.clinica.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioDTO {


    private Long id;


    private String calle;


    private String localidad;


    private String provincia;


    private Odontologo odontologo;


    private Paciente paciente;

}
