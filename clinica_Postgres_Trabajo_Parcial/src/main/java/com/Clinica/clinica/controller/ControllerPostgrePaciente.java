package com.Clinica.clinica.controller;


import com.Clinica.clinica.dto.OdontologoDTO;
import com.Clinica.clinica.dto.PacienteDTO;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.services.IOdontologoServices;
import com.Clinica.clinica.services.IPacienteServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/pacientes")
public class ControllerPostgrePaciente {

    @Autowired
    private IPacienteServices<PacienteDTO> pacienteServices;

    @Autowired
    private IOdontologoServices<OdontologoDTO> odontologoServices;

    @Autowired
    private ModelMapper mapper;

    // Listar todos los pacientes

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<Set<PacienteDTO>> listPacientes() {
        try {
            // Obtener todos los pacientes desde el servicio
            Set<PacienteDTO> pacienteDTOs = pacienteServices.findAll();

            return ResponseEntity.ok(pacienteDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Agregar un paciente
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<PacienteDTO> addPaciente (@RequestBody PacienteDTO pacienteDTO){
        pacienteServices.save(pacienteDTO);
        return ResponseEntity.ok(pacienteDTO);
    }


    // Eliminar un paciente por id
    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public void deletePaciente(@PathVariable Long id){
        Optional<PacienteDTO> paciente = Optional.ofNullable(pacienteServices.findById(id));

        if (!paciente.isPresent()){
            ResponseEntity.notFound(); // Es el status code 404
        }

        pacienteServices.delete(id);
        ResponseEntity.ok();
    }

    //Buscar un paciente especifico por id
    @CrossOrigin
    @GetMapping( value = "/list/{id}")
    public ResponseEntity<Optional<PacienteDTO>> findByIdPaciente(@PathVariable Long id){
        Optional<PacienteDTO> paciente = Optional.ofNullable(pacienteServices.findById(id));
        Optional<OdontologoDTO> odontolog = Optional.ofNullable(odontologoServices.findById(id));
        if (!paciente.isPresent()){
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(paciente);
    }

    // Modificar un paciente
    @CrossOrigin
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id , @RequestBody Paciente paciente){
        PacienteDTO p = pacienteServices.findById(id);

        if (p != null){
            p.setNombre(paciente.getNombre());
            p.setApellido(paciente.getApellido());
        }

        return ResponseEntity.ok(pacienteServices.save(p));
    }

    // Metodo para ordenar los pacientes por el nombre de forma ascendente si lo deseamos descendente
    // Se utuiliza el metodo Collections.reversed()

    @CrossOrigin
    @GetMapping("/listSortAsc")
    public ResponseEntity<Set<PacienteDTO>> listSortPacienteByNameAsc() {
        Set<PacienteDTO> pacientes = pacienteServices.findAll();

        // Convierte el Set en una lista
        List<PacienteDTO> pacienteList = new ArrayList<>(pacientes);

        // Ordena la lista por nombre ascendente
        pacienteList.sort(Comparator.comparing(PacienteDTO::getNombre));

        // Crea un nuevo Set a partir de la lista ordenada
        Set<PacienteDTO> pacientesOrdenados = new LinkedHashSet<>(pacienteList);

        return ResponseEntity.ok(pacientesOrdenados);
    }


    // Ordenar de forma descedente a los pacientes por el nombre
    @CrossOrigin
    @GetMapping("/listSortDesc")
    public ResponseEntity<Set<PacienteDTO>> listSortOdontologoByNameDesc() {
        Set<PacienteDTO> pacientes = pacienteServices.findAll();

        // Convierte el Set en una lista
        List<PacienteDTO> pacienteList = new ArrayList<>(pacientes);

        // Ordena la lista por nombre en orden descendente
        pacienteList.sort(Comparator.comparing(PacienteDTO::getNombre).reversed());

        // Convierte la lista ordenada nuevamente en un Set
        Set<PacienteDTO> pacienteSorted = new LinkedHashSet<>(pacienteList);

        return ResponseEntity.ok(pacienteSorted);
    }

    @CrossOrigin
    @DeleteMapping("deleteAll")
    public ResponseEntity<Void> deleteAll() {
        pacienteServices.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // Método para encontrar pacientes por el ID del odontólogo

    @CrossOrigin
    @GetMapping("/listByOdontologo/{odontologoId}")
    public ResponseEntity<List<PacienteDTO>> findPacientesByOdontologoId(@PathVariable Long odontologoId) {
        try {
            List<PacienteDTO> pacienteDTOs = pacienteServices.findAllByOdontologo(odontologoId);
            return ResponseEntity.ok(pacienteDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
