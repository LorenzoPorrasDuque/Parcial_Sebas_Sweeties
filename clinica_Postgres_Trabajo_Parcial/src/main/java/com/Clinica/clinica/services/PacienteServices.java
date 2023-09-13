package com.Clinica.clinica.services;

import com.Clinica.clinica.dto.PacienteDTO;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.register.RegisterPaciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PacienteServices implements IPacienteServices<PacienteDTO>{

    @Autowired
    private RegisterPaciente idaoPaciente;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    public PacienteDTO save(PacienteDTO pacienteDTO) {
        // Realiza las validaciones y lógica de negocio aquí si es necesario

        // Mapea el PacienteDTO a la entidad Paciente
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);

        // Guarda el paciente en la base de datos
        Paciente savedPaciente = idaoPaciente.save(paciente);

        // Mapea la entidad Paciente de vuelta a PacienteDTO
        return modelMapper.map(savedPaciente, PacienteDTO.class);
    }
    @Override
    public void delete(Long id) {
        idaoPaciente.deleteById(id);
    }

    @Override
    public PacienteDTO findById(Long id) {
        Optional<Paciente> paciente = idaoPaciente.findById(id);
        PacienteDTO pacienteDTO =  null;

        if (paciente.isPresent()){
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }
        return pacienteDTO;
    }

    @Override
    public Set<PacienteDTO> findAll() {
        List<Paciente> pacientes = idaoPaciente.findAll();
        Set<PacienteDTO> pacienteDTOS = new HashSet<>();

        for (Paciente i: pacientes) {
            pacienteDTOS.add(mapper.convertValue(i, PacienteDTO.class));
        }
        return pacienteDTOS;
    }

    @Override
    public void deleteAll() {
        idaoPaciente.deleteAll();
    }

    @Override
    public List<PacienteDTO> findAllByOdontologo(Long odontologoId) {
        // Utiliza el método del repositorio para encontrar los pacientes
        List<Paciente> pacientes = idaoPaciente.findAllByOdontologo(odontologoId);

        // Convierte la lista de entidades Paciente a una lista de PacienteDTO
        return pacientes.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .collect(Collectors.toList());
    }

}
