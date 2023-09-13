package com.Clinica.clinica.register;

import com.Clinica.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterPaciente extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE p.odontologo.id = :odontologoId")
    List<Paciente> findAllByOdontologo(@Param("odontologoId") Long odontologoId);


}
