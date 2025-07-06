package com.padelmons.PadelMons.repositories;

import com.padelmons.PadelMons.entities.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {

    List<Jornada> findByFaseId(Long faseId);
}
