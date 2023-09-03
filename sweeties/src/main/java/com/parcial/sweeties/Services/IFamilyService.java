package com.parcial.sweeties.Services;

import com.parcial.sweeties.Models.Family;

import java.util.List;

public interface IFamilyService {
    List<Family> get_all_families();

    Family get_family_by_id(Long id);

    Family create_family(Family family);

    void delete_family(Long id);


}
