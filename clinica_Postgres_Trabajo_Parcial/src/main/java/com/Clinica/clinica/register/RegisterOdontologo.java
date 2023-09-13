package com.Clinica.clinica.register;

import com.Clinica.clinica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterOdontologo extends JpaRepository<Odontologo,Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.nombre = :nombre")
    List<Odontologo> findAllByNombre(@Param("nombre") String nombre);

    @Query("SELECT o FROM Odontologo o WHERE o.apellido = :apellido")
    List<Odontologo> findAllByApellido(@Param("apellido") String apellido);

    @Query("SELECT o FROM Odontologo o WHERE o.matricula = :matricula")
    Odontologo findByMatricula(@Param("matricula") String matricula);

}
