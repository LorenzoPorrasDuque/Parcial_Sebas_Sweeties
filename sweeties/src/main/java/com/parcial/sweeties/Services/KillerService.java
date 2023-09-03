package com.parcial.sweeties.Services;


import com.parcial.sweeties.DAOs.IKillerDAO;
import com.parcial.sweeties.Models.Killer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KillerService implements IKillerService {
    @Autowired
    private IKillerDAO killerDAO;

    @Override
    public List<Killer> get_all_killers() {
        return killerDAO.findAll();
    }

    @Override
    public Killer get_killer_by_id(Long id) {
        return killerDAO.findById(id).orElse(null);
    }

    @Override
    public Killer create_killer(Killer killer) {
        return killerDAO.save(killer);
    }

    @Override
    public void delete_killer(Long id) {
        killerDAO.deleteById(id);
    }
}
