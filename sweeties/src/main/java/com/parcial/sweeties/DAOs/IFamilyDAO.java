package com.parcial.sweeties.DAOs;

import com.parcial.sweeties.Models.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFamilyDAO extends JpaRepository<Family, Long> {
}
