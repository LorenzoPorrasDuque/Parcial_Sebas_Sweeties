package com.Clinica.clinica.controller;

import com.Clinica.clinica.dto.OdontologoDTO;
import com.Clinica.clinica.dto.PacienteDTO;
import com.Clinica.clinica.dto.TurnoDTO;
import com.Clinica.clinica.model.Odontologo;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.model.Turno;
import com.Clinica.clinica.services.IOdontologoServices;
import com.Clinica.clinica.services.IPacienteServices;
import com.Clinica.clinica.services.ITurnoServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@RestController // Solo devuelve o acepta JSONS
@RequestMapping(value = "/turnos")
public class ControllerPostgreTurno {

    // Autowired se encarga de hacer toda la comunicacion del DAO con las capas
    @Autowired
    private ITurnoServices<TurnoDTO> turnoServices;
    @Autowired
    private IPacienteServices<PacienteDTO> pacienteServices;
    @Autowired
    private IOdontologoServices<OdontologoDTO> odontologoServices;
    @Autowired
    private ModelMapper modelMapper;

    Logger log = Logger.getLogger(String.valueOf(ControllerPostgreTurno.class));

    // Listar todos los turnos disponibles
    @CrossOrigin
    @GetMapping(value = "/list")
    public ResponseEntity<Set<TurnoDTO>> turnoList() {return ResponseEntity.ok(turnoServices.findAll());}

    // Agregar un turno a la tabla de turnos con un paciente y odontologo disponible

    @CrossOrigin
    @PostMapping(value = "/add/{idOdontologo}/{idPaciente}")
    public ResponseEntity<TurnoDTO> addTurno(@RequestBody TurnoDTO turnoDTO, @PathVariable Long idOdontologo, @PathVariable Long idPaciente) {

        if (idOdontologo == null || idPaciente == null) {
            return ResponseEntity.badRequest().build();
        }

        // Busca el odontólogo en la base de datos
        OdontologoDTO odontologoDTO = odontologoServices.findById(idOdontologo);

        if (odontologoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // Busca el paciente en la base de datos
        PacienteDTO pacienteDTO = pacienteServices.findById(idPaciente);
        if (pacienteDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // Crea un nuevo turno con los IDs del odontólogo y del paciente
        Turno turno = new Turno();
        // Mapea los objetos DTO a entidades reales
        turno.setOdontologo(modelMapper.map(odontologoDTO, Odontologo.class));
        turno.setPaciente(modelMapper.map(pacienteDTO, Paciente.class));
        turno.setFechaHora(turnoDTO.getFechaHora());

        // Guarda el turno en la base de datos
        TurnoDTO turnoGuardadoDTO = turnoServices.save(modelMapper.map(turno, TurnoDTO.class));

        return ResponseEntity.ok(turnoGuardadoDTO);
    }


    // Elminar un turno dentro de la tabla de turnos
    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public void deleteTurno(@PathVariable Long id) {

        Optional<TurnoDTO> turno = Optional.ofNullable(turnoServices.findById(id));

        if (!turno.isPresent()){
            ResponseEntity.notFound();
        }

        ResponseEntity.ok();
        turnoServices.delete(id);
    }

    // Buscar un turno por id Especifico

    @CrossOrigin
    @GetMapping(value = "/list/{id}")
    public ResponseEntity<TurnoDTO> findByIdTurno(@PathVariable Long id) {

        Optional<TurnoDTO> turno = Optional.ofNullable(turnoServices.findById(id));

        if (!turno.isPresent()){
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(turnoServices.findById(id));
    }

    // Modificar los parametros de un turno especifico

    @CrossOrigin
    @PutMapping(value = "update/{id}")
    public ResponseEntity<TurnoDTO> updateTurno(@PathVariable Long id, @RequestBody Turno turno) {

        TurnoDTO t =  turnoServices.findById(id);

        if (t != null) {
            t.setFechaHora(turno.getFechaHora());
        }

        return ResponseEntity.ok(turnoServices.save(t));
    }

    // Metodo para ordenar los turnos por la fecha de forma ascendente si lo deseamos descendente
    // Se utuiliza el metodo Collections.reversed()

    @CrossOrigin
    @GetMapping("/listSortAsc")
    public ResponseEntity<Set<TurnoDTO>> listSortPacienteByNameAsc() {
        Set<TurnoDTO> turnos = turnoServices.findAll();

        // Convierte el Set en una lista
        List<TurnoDTO> turnoList = new ArrayList<>(turnos);

        // Ordena la lista por nombre ascendente
        turnoList.sort(Comparator.comparing(TurnoDTO::getFechaHora));

        // Crea un nuevo Set a partir de la lista ordenada
        Set<TurnoDTO> turnosOrdenados = new LinkedHashSet<>(turnoList);

        return ResponseEntity.ok(turnosOrdenados);
    }


    // Ordenar de forma descedente a los turnos por la fecha

    @CrossOrigin
    @GetMapping("/listSortDesc")
    public ResponseEntity<Set<TurnoDTO>> listSortOdontologoByNameDesc() {
        Set<TurnoDTO> turnos = turnoServices.findAll();

        // Convierte el Set en una lista
        List<TurnoDTO> turnoList = new ArrayList<>(turnos);

        // Ordena la lista por nombre en orden descendente
        turnoList.sort(Comparator.comparing(TurnoDTO::getFechaHora).reversed());

        // Convierte la lista ordenada nuevamente en un Set
        Set<TurnoDTO> turnosOrdenados = new LinkedHashSet<>(turnoList);

        return ResponseEntity.ok(turnosOrdenados);
    }

    @CrossOrigin
    @DeleteMapping("deleteAll")
    public ResponseEntity<Void> deleteAll(){
        turnoServices.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // Buscar turnos por ID de Odontólogo
    @CrossOrigin
    @GetMapping(value = "/listByOdontologo/{odontologoId}")
    public ResponseEntity<List<TurnoDTO>> findAllByOdontologo(@PathVariable Long odontologoId) {
        List<TurnoDTO> turnos = turnoServices.findAllByOdontologo(odontologoId);
        if(turnos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turnos);
    }

    // Buscar turnos por ID de Paciente
    @CrossOrigin
    @GetMapping(value = "/listByPaciente/{pacienteId}")
    public ResponseEntity<List<TurnoDTO>> findAllByPaciente(@PathVariable Long pacienteId) {
        List<TurnoDTO> turnos = turnoServices.findAllByPaciente(pacienteId);
        if(turnos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turnos);
    }

    // Buscar turnos entre dos fechas
    @CrossOrigin
    @GetMapping(value = "/listBetweenDates")
    public ResponseEntity<List<TurnoDTO>> findAllBetweenDates(@RequestParam("start") String start,
                                                              @RequestParam("end") String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<TurnoDTO> turnos = turnoServices.findAllBetweenDates(startDate, endDate);
        if(turnos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turnos);
    }

}
