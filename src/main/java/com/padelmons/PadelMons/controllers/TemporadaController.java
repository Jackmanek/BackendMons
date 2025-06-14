package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.entities.Categoria;
import com.padelmons.PadelMons.entities.Fase;
import com.padelmons.PadelMons.entities.Temporada;
import com.padelmons.PadelMons.repositories.CategoriaRepository;
import com.padelmons.PadelMons.repositories.FaseRepository;
import com.padelmons.PadelMons.repositories.TemporadaRepository;
import com.padelmons.PadelMons.services.TemporadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class TemporadaController {
    @Autowired
    private TemporadaRepository temporadaRepository;
    @Autowired
    private FaseRepository faseRepository;
    @Autowired
    private TemporadaService temporadaService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/temporada")
    public ResponseEntity<Temporada> addTemporada(@RequestBody Temporada temporada) {
        Temporada newTemporada = temporadaService.crearTemporadaConFases(temporada);
        return ResponseEntity.ok(newTemporada);
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
        temporadaService.crearCategoriasParaFases(savedFase);
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

    @GetMapping("/showTemporada")
    public ResponseEntity<List<Temporada>> showTemporada() {
        List<Temporada> temporadas = temporadaRepository.findAll();
        return ResponseEntity.ok(temporadas);
    }

    @GetMapping("/showFases")
    public ResponseEntity<List<Fase>> showFases() {
        List<Fase> fases = faseRepository.findAll();
        return ResponseEntity.ok(fases);
    }

    @GetMapping("/showCategorias")
    public ResponseEntity<List<Categoria>> showCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }





}
