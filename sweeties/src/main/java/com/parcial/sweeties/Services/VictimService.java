package com.parcial.sweeties.Services;

import com.parcial.sweeties.DAOs.IVictimDAO;
import com.parcial.sweeties.Models.Victim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VictimService implements IVictimService {
    @Autowired
    private IVictimDAO victimDAO;

    @Override
    public List<Victim> get_all_victims() {
        return victimDAO.findAll();
    }

    @Override
    public Victim get_victim_by_id(Long id) {
        return victimDAO.findById(id).orElse(null);
    }

    @Override
    public void delete_victim(Long id) {
        victimDAO.deleteById(id);
    }

    @Override
    public Victim create_victim(Victim victim) {
        return victimDAO.save(victim);
    }
}
