package com.Clinica.clinica.controller;

import com.Clinica.clinica.dto.DomicilioDTO;
import com.Clinica.clinica.dto.PacienteDTO;
import com.Clinica.clinica.dto.TurnoDTO;
import com.Clinica.clinica.model.Domicilio;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.services.IDomicilioServices;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/domicilios")
public class ControllerPostgreDomicilio {

    @Autowired
    private IDomicilioServices<DomicilioDTO> domicilioServices;

    @CrossOrigin
    @GetMapping(value = "/list")
    public ResponseEntity<Set<DomicilioDTO>> listDomicilios(){ return ResponseEntity.ok(domicilioServices.findAll());}

    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<DomicilioDTO> addDomicilio(@RequestBody DomicilioDTO domicilioDTO){
        domicilioServices.save(domicilioDTO);
        return ResponseEntity.ok(domicilioDTO);
    }

    @CrossOrigin
    @GetMapping(value = "/list/{id}")
    public  ResponseEntity<DomicilioDTO> findById(@PathVariable Long id){

        Optional<DomicilioDTO> domicilioDTO = Optional.ofNullable(domicilioServices.findById(id));

        if (!domicilioDTO.isPresent()){
             ResponseEntity.notFound();
        }

        return ResponseEntity.ok(domicilioServices.findById(id));
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id){

        Optional<DomicilioDTO> domicilioDTO = Optional.ofNullable(domicilioServices.findById(id));

        if (!domicilioDTO.isPresent()){
            ResponseEntity.notFound();
        }

        domicilioServices.delete(id);
    }

    @CrossOrigin
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<DomicilioDTO> updatePaciente(@PathVariable Long id , @RequestBody Domicilio domicilio){
        DomicilioDTO d = domicilioServices.findById(id);

        if (d != null){
            d.setCalle(domicilio.getCalle());
            d.setProvincia(domicilio.getProvincia());
            d.setLocalidad(domicilio.getLocalidad());
        }

        return ResponseEntity.ok(domicilioServices.save(d));
    }

    @CrossOrigin
    @GetMapping("/listSortAsc")
    public ResponseEntity<Set<DomicilioDTO>> listSortPacienteByNameAsc() {
        Set<DomicilioDTO> domicilios = domicilioServices.findAll();

        // Convierte el Set en una lista
        List<DomicilioDTO> domicilioList = new ArrayList<>(domicilios);

        // Ordena la lista por nombre ascendente
        domicilioList.sort(Comparator.comparing(DomicilioDTO::getProvincia));

        // Crea un nuevo Set a partir de la lista ordenada
        Set<DomicilioDTO> domicliosOrdenados = new LinkedHashSet<>(domicilioList);

        return ResponseEntity.ok(domicliosOrdenados);
    }


    // Ordenar de forma descedente a los turnos por la fecha

    @CrossOrigin
    @GetMapping("/listSortDesc")
    public ResponseEntity<Set<DomicilioDTO>> listSortOdontologoByNameDesc() {
        Set<DomicilioDTO> domicilios = domicilioServices.findAll();

        // Convierte el Set en una lista
        List<DomicilioDTO> domicilioList = new ArrayList<>(domicilios);

        // Ordena la lista por nombre en orden descendente
        domicilioList.sort(Comparator.comparing(DomicilioDTO::getProvincia).reversed());

        // Convierte la lista ordenada nuevamente en un Set
        Set<DomicilioDTO> domiciliosOrdenados = new LinkedHashSet<>(domicilioList);

        return ResponseEntity.ok(domiciliosOrdenados);
    }

    @CrossOrigin
    @DeleteMapping("deleteAll")
    public ResponseEntity<Void> deleteAll(){
        domicilioServices.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping("/findByProvincia/{provincia}")
    public ResponseEntity<List<DomicilioDTO>> findByProvincia(@PathVariable String provincia) {
        List<DomicilioDTO> domicilios = domicilioServices.findAllByProvincia(provincia);
        if (domicilios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(domicilios);
    }

    @CrossOrigin
    @GetMapping("/findByLocalidad/{localidad}")
    public ResponseEntity<List<DomicilioDTO>> findByLocalidad(@PathVariable String localidad) {
        List<DomicilioDTO> domicilios = domicilioServices.findAllByLocalidad(localidad);
        if (domicilios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(domicilios);
    }


}
