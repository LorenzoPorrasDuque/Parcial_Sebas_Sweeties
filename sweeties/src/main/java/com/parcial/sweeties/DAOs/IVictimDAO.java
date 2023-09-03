package com.parcial.sweeties.DAOs;

import com.parcial.sweeties.Models.Victim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVictimDAO extends JpaRepository<Victim, Long> {
}
