package com.Clinica.clinica.services;

import com.Clinica.clinica.dto.TurnoDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ITurnoServices <T> {

    T save(T t);

    void delete(Long id);

    T findById(Long id);

    Set<T> findAll();

    void deleteAll();


    List<TurnoDTO> findAllByOdontologo(Long odontologoId);

    List<TurnoDTO> findAllByPaciente(Long pacienteId);

    List<TurnoDTO> findAllBetweenDates(LocalDateTime start, LocalDateTime end);
}
