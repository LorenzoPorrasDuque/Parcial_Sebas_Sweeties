package com.parcial.sweeties.Controllers;

import com.parcial.sweeties.Models.Family;
import com.parcial.sweeties.Services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    // Esta mierda la uso en varias clases, no se si esta chimba pero funciona :v
    static boolean hasErrorsFunction(BindingResult result, Map<String, Object> response) {
        if (result.hasErrors()) {
            List<String> error = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) {
                error.add("el campo" + err.getField() + " " + err.getDefaultMessage());
            }
            response.put("Errors", error);
            return true;
        }
        return false;
    }

    @GetMapping("/list")
    public List<Family> FamilyList() {
        List<Family> list = familyService.get_all_families();
        return list;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> Familyid(@PathVariable Long id) {
        Family Family = familyService.get_family_by_id(id);
        Map<String, String> response = new HashMap<>();
        if (Family == null) {
            response.put("Mensaje", "La familia con id " + id + " no existe");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Family>(Family, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        familyService.delete_family(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addFamily(@Valid @RequestBody Family Family, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Family lord = null;
        if (hasErrorsFunction(result, response))
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        try {
            lord = familyService.create_family(Family);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Family>(lord, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Family update(@PathVariable Long id, @RequestBody Family Family) {
        Family actual = familyService.get_family_by_id(id);
        if (actual != null) {
            actual.setName(Family.getName());
            actual.setNumber_members(Family.getNumber_members());
        }
        return familyService.create_family(actual);
    }
}
