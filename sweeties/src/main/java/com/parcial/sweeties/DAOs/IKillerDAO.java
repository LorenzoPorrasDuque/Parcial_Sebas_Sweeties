package com.parcial.sweeties.DAOs;

import com.parcial.sweeties.Models.Killer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKillerDAO extends JpaRepository<Killer, Long> {
}
