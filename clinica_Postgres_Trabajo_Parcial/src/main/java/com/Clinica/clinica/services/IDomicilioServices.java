package com.Clinica.clinica.services;


import java.util.List;
import java.util.Set;

public interface IDomicilioServices<T> {

    T save (T t);

    void delete (Long id);

    T findById (Long id);

    Set<T> findAll ();

    void deleteAll();

    List<T> findAllByProvincia(String provincia);

    List<T> findAllByLocalidad(String localidad);




}
