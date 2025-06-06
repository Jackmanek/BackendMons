package com.padelmons.PadelMons.repositories;

import com.padelmons.PadelMons.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
