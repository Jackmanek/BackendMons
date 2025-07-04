package com.padelmons.PadelMons.services;

import com.padelmons.PadelMons.Dto.MatchDTO;
import com.padelmons.PadelMons.entities.Jornada;
import com.padelmons.PadelMons.entities.Match;
import com.padelmons.PadelMons.entities.Team;
import com.padelmons.PadelMons.repositories.JornadaRepository;
import com.padelmons.PadelMons.repositories.MatchRepository;
import com.padelmons.PadelMons.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private final TeamRepository teamRepository;
    private final JornadaRepository jornadaRepository;
    private final MatchRepository matchRepository;

    public MatchService(TeamRepository teamRepository,
                        JornadaRepository jornadaRepository,
                        MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.jornadaRepository = jornadaRepository;
        this.matchRepository = matchRepository;
    }

    public Match crearMatch(MatchDTO dto) {
        Team local = teamRepository.findById(dto.localTeamId)
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));

        Team visitor = teamRepository.findById(dto.visitorTeamId)
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));

        Jornada jornada = jornadaRepository.findById(dto.jornadaId)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        Match match = new Match();
        match.setLocal(local);
        match.setVisitor(visitor);
        match.setJornada(jornada);
        match.setFecha(dto.fecha);
        match.setFinalizado(false);

        return matchRepository.save(match);
    }

    public List<Match> listarTodosLosPartidos() {
        return matchRepository.findAll();
    }

    public Match obtenerMatchPorId(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
    }

    public Match actualizarResultado(Long matchId, boolean finalizado) {
        Match match = obtenerMatchPorId(matchId);
        match.setFinalizado(finalizado);
        return matchRepository.save(match);
    }
}
