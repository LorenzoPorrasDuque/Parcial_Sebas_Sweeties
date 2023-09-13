package com.Clinica.clinica.services;

import com.Clinica.clinica.dto.TurnoDTO;
import com.Clinica.clinica.model.Turno;
import com.Clinica.clinica.register.RegisterTurno;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TurnoServices implements ITurnoServices<TurnoDTO>{

    @Autowired
    private RegisterTurno idaOTurno;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public TurnoDTO save(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        idaOTurno.save(turno);
        return turnoDTO;
    }

    @Override
    public void delete(Long id) {
        idaOTurno.deleteById(id);
    }

    @Override
    public TurnoDTO findById(Long id) {
        Optional<Turno> turno = idaOTurno.findById(id);
        TurnoDTO turnoDTO = null;
        if (turno.isPresent()){
            turnoDTO =  mapper.convertValue(turno, TurnoDTO.class);
        }
        return turnoDTO;
    }

    @Override
    public Set<TurnoDTO> findAll() {
        List<Turno> turnos = idaOTurno.findAll();
        Set<TurnoDTO> turnoDTOS = new HashSet<>();

        for (Turno i: turnos) {
            turnoDTOS.add(mapper.convertValue(i, TurnoDTO.class));
        }
        return turnoDTOS;
    }

    @Override
    public void deleteAll() {
        idaOTurno.deleteAll();
    }

    @Override
    public List<TurnoDTO> findAllByOdontologo(Long odontologoId) {
        List<Turno> turnos = idaOTurno.findAllByOdontologo(odontologoId);
        List<TurnoDTO> turnoDTOS = new ArrayList<>();

        for (Turno i: turnos) {
            turnoDTOS.add(mapper.convertValue(i, TurnoDTO.class));
        }
        return turnoDTOS;
    }

    @Override
    public List<TurnoDTO> findAllByPaciente(Long pacienteId) {
        List<Turno> turnos = idaOTurno.findAllByPaciente(pacienteId);
        List<TurnoDTO> turnoDTOS = new ArrayList<>();

        for (Turno i: turnos) {
            turnoDTOS.add(mapper.convertValue(i, TurnoDTO.class));
        }
        return turnoDTOS;
    }

    @Override
    public List<TurnoDTO> findAllBetweenDates(LocalDateTime start, LocalDateTime end) {
        List<Turno> turnos = idaOTurno.findAllBetweenDates(start, end);
        List<TurnoDTO> turnoDTOS = new ArrayList<>();

        for (Turno i: turnos) {
            turnoDTOS.add(mapper.convertValue(i, TurnoDTO.class));
        }
        return turnoDTOS;
    }


}
