package com.parcial.sweeties.Services;

import com.parcial.sweeties.Models.Killer;

import java.util.List;

public interface IKillerService {

    List<Killer> get_all_killers();

    Killer get_killer_by_id(Long id);

    Killer create_killer(Killer killer);

    void delete_killer(Long id);


}
