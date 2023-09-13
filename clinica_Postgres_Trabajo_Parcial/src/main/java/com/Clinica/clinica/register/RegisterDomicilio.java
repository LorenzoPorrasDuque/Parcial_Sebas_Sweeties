package com.Clinica.clinica.register;

import com.Clinica.clinica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RegisterDomicilio extends JpaRepository<Domicilio, Long> {

    @Query("SELECT d FROM Domicilio d WHERE d.provincia = :provincia")
    List<Domicilio> findAllByProvincia(@Param("provincia") String provincia);

    @Query("SELECT d FROM Domicilio d WHERE d.localidad = :localidad")
    List<Domicilio> findAllByLocalidad(@Param("localidad") String localidad);


}
