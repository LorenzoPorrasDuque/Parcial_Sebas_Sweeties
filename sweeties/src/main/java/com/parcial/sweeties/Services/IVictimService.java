package com.parcial.sweeties.Services;

import com.parcial.sweeties.Models.Victim;

import java.util.List;

public interface IVictimService {
    List<Victim> get_all_victims();

    Victim get_victim_by_id(Long id);

    void delete_victim(Long id);

    Victim create_victim(Victim victim);
}
