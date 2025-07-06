package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.Dto.JornadaDTO;
import com.padelmons.PadelMons.entities.Jornada;
import com.padelmons.PadelMons.services.JornadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/auth")
public class JornadaController {

    private final JornadaService jornadaService;

    public JornadaController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @PostMapping("/createjornada")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Jornada> crear(@RequestBody JornadaDTO dto) {
        return ResponseEntity.ok(jornadaService.crearJornada(dto));
    }

    @GetMapping("/jornadas")
    public List<Jornada> listarTodas() {
        return jornadaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jornada> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jornadaService.obtenerPorId(id));
    }

    @GetMapping("/fase/{faseId}")
    public List<Jornada> listarPorFase(@PathVariable Long faseId) {
        return jornadaService.listarPorFase(faseId);
    }
}
