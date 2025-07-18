package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.Dto.TeamDTO;
import com.padelmons.PadelMons.Dto.TeamResponseDTO;
import com.padelmons.PadelMons.entities.Categoria;
import com.padelmons.PadelMons.entities.Player;
import com.padelmons.PadelMons.entities.Team;
import com.padelmons.PadelMons.repositories.CategoriaRepository;
import com.padelmons.PadelMons.repositories.PlayerRepository;
import com.padelmons.PadelMons.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createteam")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO dto) {
        Categoria catego = categoriaRepository.findById(dto.categoriaId)
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
        Team team = new Team(dto.name);
        team.setCategoria(catego);
        List<Player> players = dto.playerIds.stream()
                .map(id -> playerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + id)))
                .peek(player -> player.setTeam(team))
                .collect(Collectors.toList());

        team.setPlayers(players);
        Team savedTeam = teamRepository.save(team);

        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponseDTO>> mostrarTeams(){
        List<TeamResponseDTO> teamDTOs = teamRepository.findAll().stream()
                .map(TeamResponseDTO::new)
                .collect(Collectors.toList());
        System.out.println("hika "+ teamDTOs.toString());

        return ResponseEntity.ok(teamDTOs);
    }
}
