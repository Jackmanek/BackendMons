package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.entities.Fase;
import com.padelmons.PadelMons.entities.Temporada;
import com.padelmons.PadelMons.repositories.FaseRepository;
import com.padelmons.PadelMons.repositories.TemporadaRepository;
import com.padelmons.PadelMons.services.TemporadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class TemporadaController {
    @Autowired
    private TemporadaRepository temporadaRepository;
    @Autowired
    private FaseRepository faseRepository;
    @Autowired
    private TemporadaService temporadaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/temporada")
    public Temporada addTemporada(@RequestBody Temporada temporada) {
        return temporadaRepository.save(temporada);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/fase")
    public ResponseEntity<?> addFase(@RequestBody Fase fase) {
        Long temporadaId = fase.getTemporada() != null ? fase.getTemporada().getId() : null;

        if (temporadaId == null || !temporadaRepository.existsById(temporadaId)) {
            return ResponseEntity.badRequest().body("La temporada asociada no existe");
        }

        Temporada temporada = temporadaRepository.findById(temporadaId).orElse(null);
        fase.setTemporada(temporada);

        Fase savedFase = faseRepository.save(fase);
        return ResponseEntity.ok(savedFase);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/fase/{faseId}/crea-categorias")
    public ResponseEntity<String> cerarCategorias(@PathVariable Long faseId) {
        Fase fase = faseRepository.findById(faseId).orElseThrow(()-> new RuntimeException("Fase no encontrado"));
        temporadaService.crearCategoriasParaFases(fase);
        return ResponseEntity.ok("Categorias creadas para la fase");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/fase/{faseId}/mover-equipos")
    public ResponseEntity<String> moverEquipos(@PathVariable Long faseId) {
        Fase fase = faseRepository.findById(faseId).orElseThrow(() -> new RuntimeException("Fase no encontrada"));
        temporadaService.aplicarAscensoYDescensos(fase);
        return ResponseEntity.ok("Ascensos y descensos aplicados.");
    }





}
