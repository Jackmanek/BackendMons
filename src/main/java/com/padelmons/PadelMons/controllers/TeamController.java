package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.Dto.TeamDTO;
import com.padelmons.PadelMons.entities.Player;
import com.padelmons.PadelMons.entities.Team;
import com.padelmons.PadelMons.repositories.PlayerRepository;
import com.padelmons.PadelMons.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("createteam")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO dto) {
        Team team = new Team(dto.name);

        List<Player> players = dto.playerIds.stream()
                .map(id -> playerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + id)))
                .peek(player -> player.setTeam(team))
                .collect(Collectors.toList());

        team.setPlayers(players);
        Team savedTeam = teamRepository.save(team);

        return ResponseEntity.ok(savedTeam);
    }
}
