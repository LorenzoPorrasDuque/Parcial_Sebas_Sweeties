package com.Clinica.clinica.register;

import com.Clinica.clinica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegisterTurno extends JpaRepository<Turno,Long> {

    @Query("SELECT t FROM Turno t WHERE t.odontologo.id = :odontologoId")
    List<Turno> findAllByOdontologo(@Param("odontologoId") Long odontologoId);

    @Query("SELECT t FROM Turno t WHERE t.paciente.id = :pacienteId")
    List<Turno> findAllByPaciente(@Param("pacienteId") Long pacienteId);

    @Query("SELECT t FROM Turno t WHERE t.fechaHora BETWEEN :start AND :end")
    List<Turno> findAllBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


}
