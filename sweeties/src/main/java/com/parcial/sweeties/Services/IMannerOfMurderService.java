package com.parcial.sweeties.Services;

import com.parcial.sweeties.Models.MannerOfMurder;

import java.util.List;

public interface IMannerOfMurderService {
    List<MannerOfMurder> get_all_manner_of_murders();

    MannerOfMurder get_manner_of_murder_by_id(Long id);

    void delete_manner(Long id);

    MannerOfMurder create_manner(MannerOfMurder manner_of_murder);


}
