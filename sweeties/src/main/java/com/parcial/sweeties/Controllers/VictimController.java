package com.parcial.sweeties.Controllers;

import com.parcial.sweeties.Models.Victim;
import com.parcial.sweeties.Services.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/victim")
public class VictimController {
    @Autowired
    private VictimService victimService;

    @GetMapping("/list")
    public List<Victim> FamilyList() {
        List<Victim> list = victimService.get_all_victims();
        return list;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> Familyid(@PathVariable Long id) {
        Victim Victim = victimService.get_victim_by_id(id);
        Map<String, String> response = new HashMap<>();
        if (Victim == null) {
            response.put("Mensaje", "El Victim con id " + id + " no existe");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Victim>(Victim, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        victimService.delete_victim(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addFamily(@Valid @RequestBody Victim Victim, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Victim lord = null;
        if (FamilyController.hasErrorsFunction(result, response))
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        try {
            lord = victimService.create_victim(Victim);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Victim>(lord, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Victim update(@PathVariable Long id, @RequestBody Victim Victim) {
        Victim actual = victimService.get_victim_by_id(id);
        if (actual != null) {
            actual.setName(Victim.getName());
            actual.setLast_name(Victim.getLast_name());
            actual.setAge(Victim.getAge());
            actual.setOccupation(Victim.getOccupation());
            actual.setDescription(Victim.getDescription());
        }
        return victimService.create_victim(actual);
    }
}
