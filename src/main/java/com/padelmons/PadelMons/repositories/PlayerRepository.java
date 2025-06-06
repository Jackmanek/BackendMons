package com.padelmons.PadelMons.repositories;

import com.padelmons.PadelMons.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
