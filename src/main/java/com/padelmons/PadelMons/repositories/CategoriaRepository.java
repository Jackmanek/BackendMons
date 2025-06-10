package com.padelmons.PadelMons.repositories;

import com.padelmons.PadelMons.entities.Categoria;
import com.padelmons.PadelMons.entities.Fase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByFaseOrderByOrdenAsc(Fase fase);
}
