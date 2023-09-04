package com.parcial.sweeties.Controllers;

import com.parcial.sweeties.Models.MannerOfMurder;
import com.parcial.sweeties.Services.MannerOfMurderService;
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
@RequestMapping("/mannerOfMurder")
public class MannerOfMurderController {
    @Autowired
    private MannerOfMurderService mannerOfMurderService;

    @GetMapping("/list")
    public List<MannerOfMurder> FamilyList() {
        List<MannerOfMurder> list = mannerOfMurderService.get_all_manner_of_murders();
        return list;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> Familyid(@PathVariable Long id) {
        MannerOfMurder MannerOfMurder = mannerOfMurderService.get_manner_of_murder_by_id(id);
        Map<String, String> response = new HashMap<>();
        if (MannerOfMurder == null) {
            response.put("Mensaje", "El MannerOfMurder con id " + id + " no existe");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MannerOfMurder>(MannerOfMurder, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        mannerOfMurderService.delete_manner(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addFamily(@Valid @RequestBody MannerOfMurder MannerOfMurder, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        MannerOfMurder lord = null;
        if (FamilyController.hasErrorsFunction(result, response))
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        try {
            lord = mannerOfMurderService.create_manner(MannerOfMurder);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MannerOfMurder>(lord, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MannerOfMurder update(@PathVariable Long id, @RequestBody MannerOfMurder MannerOfMurder) {
        MannerOfMurder actual = mannerOfMurderService.get_manner_of_murder_by_id(id);
        if (actual != null) {
            actual.setType(MannerOfMurder.getType());
            actual.setDescription(MannerOfMurder.getDescription());


        }
        return mannerOfMurderService.create_manner(actual);
    }
}
