package com.padelmons.PadelMons.repositories;

import com.padelmons.PadelMons.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
