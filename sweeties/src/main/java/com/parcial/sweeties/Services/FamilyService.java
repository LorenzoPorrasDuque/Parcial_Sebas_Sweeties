package com.parcial.sweeties.Services;

import com.parcial.sweeties.DAOs.IFamilyDAO;
import com.parcial.sweeties.Models.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FamilyService implements IFamilyService {
    @Autowired
    private IFamilyDAO familyDAO;

    @Override
    public List<Family> get_all_families() {
        return familyDAO.findAll();
    }

    @Override
    public Family get_family_by_id(Long id) {
        return familyDAO.findById(id).orElse(null);
    }

    @Override
    public Family create_family(Family family) {
        return familyDAO.save(family);
    }

    @Override
    public void delete_family(Long id) {
        familyDAO.deleteById(id);
    }
}
