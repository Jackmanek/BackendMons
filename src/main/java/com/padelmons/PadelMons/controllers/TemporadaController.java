package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.entities.Temporada;
import com.padelmons.PadelMons.repositories.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temporadas")
public class TemporadaController {
    @Autowired
    private TemporadaRepository temporadaRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Temporada addTemporada(@RequestBody Temporada temporada) {
        return temporadaRepository.save(temporada);
    }
}
