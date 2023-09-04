package com.parcial.sweeties.Controllers;

import com.parcial.sweeties.Models.Killer;
import com.parcial.sweeties.Services.KillerService;
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
@RequestMapping("/killer")
public class KillerController {
    @Autowired
    private KillerService killerService;

    @GetMapping("/list")
    public List<Killer> FamilyList() {
        List<Killer> list = killerService.get_all_killers();
        return list;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> Familyid(@PathVariable Long id) {
        Killer Killer = killerService.get_killer_by_id(id);
        Map<String, String> response = new HashMap<>();
        if (Killer == null) {
            response.put("Mensaje", "El Killer con id " + id + " no existe");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Killer>(Killer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        killerService.delete_killer(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addFamily(@Valid @RequestBody Killer Killer, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Killer lord = null;
        if (FamilyController.hasErrorsFunction(result, response))
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        try {
            lord = killerService.create_killer(Killer);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Killer>(lord, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Killer update(@PathVariable Long id, @RequestBody Killer Killer) {
        Killer actual = killerService.get_killer_by_id(id);
        if (actual != null) {
            actual.setName(Killer.getName());
            actual.setLast_name(Killer.getLast_name());
            actual.setNickname(Killer.getNickname());
            actual.setDescription(Killer.getDescription());
        }
        return killerService.create_killer(actual);
    }
}
