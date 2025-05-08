package com.Clinica.clinica.controller;

import com.Clinica.clinica.dto.OdontologoDTO;
import com.Clinica.clinica.dto.PacienteDTO;
import com.Clinica.clinica.model.Odontologo;
import com.Clinica.clinica.model.Paciente;
import com.Clinica.clinica.services.IOdontologoServices;
import com.Clinica.clinica.services.IPacienteServices;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/odontologos")
public class ControllerPostgreOdontologo {

    @Autowired
    private IOdontologoServices<OdontologoDTO> odontologoServices;

    @Autowired
    private IPacienteServices<PacienteDTO> pacienteServices;

    @Autowired
    private ModelMapper mapper;

    // Metodo de buscar a todos los odontologos en la tabla de odontologos en la base de datos

    // Configurar bien corse para puertos especificos
    @CrossOrigin
    @GetMapping("/list")
    public Set<OdontologoDTO> odontologoList() {return odontologoServices.findAll();}

    // Metodo para buscar un odontolo en la en la tabla de odontologos de la base de datos

    @CrossOrigin
    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OdontologoDTO odontologoid(@PathVariable Long id) throws Exception {return odontologoServices.findById(id);}

    // Metodo eliminar en la tabla odontologos en la base de datos

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){

        Optional<OdontologoDTO> odontologo = Optional.ofNullable(odontologoServices.findById(id));

        if (!odontologo.isPresent()) {
            ResponseEntity.notFound();
        }
        odontologoServices.delete(id);

    }

    // Agregar a la tabla odontologos de la base de datos

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<OdontologoDTO> addOdontologo (@RequestBody OdontologoDTO odontologoDTO){
        odontologoServices.save(odontologoDTO);
        return ResponseEntity.ok(odontologoDTO);
    }

    // Agregar a la tabla odontologos de la base de datos si existen pacientes

    // Metodo de actualizar la base de datos

    @CrossOrigin
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OdontologoDTO update(@PathVariable Long id, @RequestBody OdontologoDTO odontologo){
        OdontologoDTO o = odontologoServices.findById(id);
        if (o != null){
            o.setNombre(odontologo.getNombre());
            o.setApellido(odontologo.getApellido());
        }
        return odontologoServices.save(o);
    }

    // Metodo para ordenar los odontologos por el nombre de forma ascendente si lo deseamos descendente
    // Se utuiliza el metodo Collections.reversed()

    @CrossOrigin
    @GetMapping("/listSortAsc")
    public ResponseEntity<Set<OdontologoDTO>> listSortOdontologoByNameAsc() {
        Set<OdontologoDTO> odontologos = odontologoServices.findAll();

        // Convierte el Set en una lista
        List<OdontologoDTO> odontologoList = new ArrayList<>(odontologos);

        // Ordena la lista por nombre ascendente
        odontologoList.sort(Comparator.comparing(OdontologoDTO::getNombre));

        // Crea un nuevo Set a partir de la lista ordenada
        Set<OdontologoDTO> odontologosOrdenados = new LinkedHashSet<>(odontologoList);

        return ResponseEntity.ok(odontologosOrdenados);
    }


    // Ordenar de forma descedente a los odontologos por el nombre

    @CrossOrigin
    @GetMapping("/listSortDesc")
    public ResponseEntity<Set<OdontologoDTO>> listSortOdontologoByNameDesc() {
        Set<OdontologoDTO> odontologos = odontologoServices.findAll();

        // Convierte el Set en una lista
        List<OdontologoDTO> odontologoList = new ArrayList<>(odontologos);

        // Ordena la lista por nombre en orden descendente
        odontologoList.sort(Comparator.comparing(OdontologoDTO::getNombre).reversed());

        // Convierte la lista ordenada nuevamente en un Set
        Set<OdontologoDTO> odontologoSortedSet = new LinkedHashSet<>(odontologoList);

        return ResponseEntity.ok(odontologoSortedSet);
    }

    @CrossOrigin
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        odontologoServices.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping("/listByNombre")
    public ResponseEntity<List<OdontologoDTO>> listByNombre(@RequestParam String nombre) {
        List<OdontologoDTO> odontologos = odontologoServices.findAllByNombre(nombre);
        return new ResponseEntity<>(odontologos, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/listByApellido")
    public ResponseEntity<List<OdontologoDTO>> listByApellido(@RequestParam String apellido) {
        List<OdontologoDTO> odontologos = odontologoServices.findAllByApellido(apellido);
        return new ResponseEntity<>(odontologos, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/listByMatricula")
    public ResponseEntity<OdontologoDTO> listByMatricula(@RequestParam String matricula) {
        OdontologoDTO odontologo = odontologoServices.findByMatricula(matricula);
        if (odontologo != null) {
            return new ResponseEntity<>(odontologo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
