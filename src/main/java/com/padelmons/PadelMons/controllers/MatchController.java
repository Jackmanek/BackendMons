package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.Dto.MatchDTO;
import com.padelmons.PadelMons.Dto.ResultadoPartidoDTO;
import com.padelmons.PadelMons.entities.Match;
import com.padelmons.PadelMons.services.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/creatematch")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Match> crearMatch(@RequestBody MatchDTO dto) {
        Match nuevoMatch = matchService.crearMatch(dto);
        return ResponseEntity.ok(nuevoMatch);
    }
    @GetMapping("/matches")
    public List<Match> listarPartidos() {
        return matchService.listarTodosLosPartidos();
    }

    @GetMapping("/match/{id}")
    public ResponseEntity<Match> obtenerMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.obtenerMatchPorId(id));
    }

    @PutMapping("/{id}/finalizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Match> finalizarMatch(@PathVariable Long id, @RequestParam boolean finalizado) {
        return ResponseEntity.ok(matchService.actualizarResultado(id, finalizado));
    }

    @PostMapping("/resultado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> guardarResultado(@RequestBody ResultadoPartidoDTO dto) {
        matchService.guardarResultado(dto);
        return ResponseEntity.ok("Resultado del partido guardado correctamente.");
    }
}
