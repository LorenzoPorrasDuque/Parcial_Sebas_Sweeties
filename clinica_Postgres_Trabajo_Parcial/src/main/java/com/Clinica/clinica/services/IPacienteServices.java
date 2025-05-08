package com.Clinica.clinica.services;


import com.Clinica.clinica.dto.PacienteDTO;
import com.Clinica.clinica.model.Paciente;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

public interface IPacienteServices<T> {

    T save(T t);

    void delete(Long id);

    T findById(Long id);

    Set<T> findAll();

    void deleteAll();

    List<T> findAllByOdontologo(Long odontologoId);


}
