package com.Clinica.clinica.services;

import com.Clinica.clinica.dto.DomicilioDTO;
import com.Clinica.clinica.model.Domicilio;
import com.Clinica.clinica.register.RegisterDomicilio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomicilioServices implements IDomicilioServices<DomicilioDTO> {

    @Autowired
    private RegisterDomicilio idaoDomicilio;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DomicilioDTO save(DomicilioDTO domicilioDTO) {
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        idaoDomicilio.save(domicilio);
        return domicilioDTO;
    }

    @Override
    public void delete(Long id) {
        idaoDomicilio.deleteById(id);
    }

    @Override
    public DomicilioDTO findById(Long id) {
        Optional<Domicilio> domicilio = idaoDomicilio.findById(id);
        if (domicilio.isPresent()){
            return mapper.convertValue(domicilio.get(), DomicilioDTO.class);
        }
        return null;
    }

    @Override
    public Set<DomicilioDTO> findAll() {
        List<Domicilio> domicilioList = idaoDomicilio.findAll();
        Set<DomicilioDTO> domicilioDTOS = new HashSet<>();
        for (Domicilio i: domicilioList) {
            domicilioDTOS.add(mapper.convertValue(i,DomicilioDTO.class));
        }
        return domicilioDTOS;
    }

    @Override
    public void deleteAll() {
        idaoDomicilio.deleteAll();
    }

    @Override
    public List<DomicilioDTO> findAllByProvincia(String provincia) {
        List<Domicilio> domicilios = idaoDomicilio.findAllByProvincia(provincia);
        List<DomicilioDTO> domicilioDTOS = new ArrayList<>();
        for (Domicilio i : domicilios) {
            domicilioDTOS.add(mapper.convertValue(i, DomicilioDTO.class));
        }
        return domicilioDTOS;
    }

    @Override
    public List<DomicilioDTO> findAllByLocalidad(String localidad) {
        List<Domicilio> domicilios = idaoDomicilio.findAllByLocalidad(localidad);
        List<DomicilioDTO> domicilioDTOS = new ArrayList<>();
        for (Domicilio i : domicilios) {
            domicilioDTOS.add(mapper.convertValue(i, DomicilioDTO.class));
        }
        return domicilioDTOS;
    }


}
