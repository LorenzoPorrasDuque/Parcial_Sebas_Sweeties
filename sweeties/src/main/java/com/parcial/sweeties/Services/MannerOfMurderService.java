package com.parcial.sweeties.Services;

import com.parcial.sweeties.DAOs.IMannerOfMurderDAO;
import com.parcial.sweeties.Models.MannerOfMurder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MannerOfMurderService implements IMannerOfMurderService {
    @Autowired
    private IMannerOfMurderDAO manner_Of_Murder_ServiceDAO;

    @Override
    public List<MannerOfMurder> get_all_manner_of_murders() {
        return manner_Of_Murder_ServiceDAO.findAll();
    }

    @Override
    public MannerOfMurder get_manner_of_murder_by_id(Long id) {
        return manner_Of_Murder_ServiceDAO.findById(id).orElse(null);
    }

    @Override
    public void delete_manner(Long id) {
        manner_Of_Murder_ServiceDAO.deleteById(id);
    }

    @Override
    public MannerOfMurder create_manner(MannerOfMurder manner_of_murder) {
        return manner_Of_Murder_ServiceDAO.save(manner_of_murder);
    }
}
