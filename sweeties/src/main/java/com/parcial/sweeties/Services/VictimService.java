package com.parcial.sweeties.Services;

import com.parcial.sweeties.DAOs.IVictimDAO;
import com.parcial.sweeties.Models.Victim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> search_family(Long id) {
        return victimDAO.findVictimDataById(id);
    }

    public List<Map<String, Object>> killer_manners(Long id) {
        return victimDAO.killer_manners_of_kill(id);
    }

    public List<Map<String, Object>> manner_victim(Long id) {
        return victimDAO.victim_manner_of_death(id);
    }

    public List<Map<String, Object>> killer_victim(Long id) {
        return victimDAO.killer_of_a_victim(id);
    }
}
