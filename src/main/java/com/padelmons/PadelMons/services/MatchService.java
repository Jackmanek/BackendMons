package com.padelmons.PadelMons.services;

import com.padelmons.PadelMons.Dto.MatchDTO;
import com.padelmons.PadelMons.Dto.ResultadoPartidoDTO;
import com.padelmons.PadelMons.Dto.SetResultadoDTO;
import com.padelmons.PadelMons.entities.Jornada;
import com.padelmons.PadelMons.entities.Match;
import com.padelmons.PadelMons.entities.SetMatch;
import com.padelmons.PadelMons.entities.Team;
import com.padelmons.PadelMons.repositories.JornadaRepository;
import com.padelmons.PadelMons.repositories.MatchRepository;
import com.padelmons.PadelMons.repositories.SetMatchRepository;
import com.padelmons.PadelMons.repositories.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private final TeamRepository teamRepository;
    private final JornadaRepository jornadaRepository;
    private final MatchRepository matchRepository;
    private final SetMatchRepository setMatchRepository;

    public MatchService(TeamRepository teamRepository,
                        JornadaRepository jornadaRepository,
                        MatchRepository matchRepository,
                        SetMatchRepository setMatchRepository) {
        this.teamRepository = teamRepository;
        this.jornadaRepository = jornadaRepository;
        this.matchRepository = matchRepository;
        this.setMatchRepository = setMatchRepository;
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
    @Transactional
    public void guardarResultado(ResultadoPartidoDTO dto) {
        Match match = matchRepository.findById(dto.matchId)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        // Borra sets anteriores si existen (opcional según tu lógica)
        setMatchRepository.deleteAll(match.getSets());

        int setsGanadosLocal = 0;
        int setsGanadosVisitante = 0;

        for (SetResultadoDTO setDto : dto.sets) {
            SetMatch set = new SetMatch(
                    setDto.numSet,
                    setDto.gameLocal,
                    setDto.gameVisit,
                    match
            );
            setMatchRepository.save(set);

            if (setDto.gameLocal > setDto.gameVisit) setsGanadosLocal++;
            else if (setDto.gameVisit > setDto.gameLocal) setsGanadosVisitante++;
        }

        match.setFinalizado(true);
        matchRepository.save(match);

        // Puntos por partido (por ejemplo: 3 puntos al ganador)
        if (setsGanadosLocal > setsGanadosVisitante) {
            Team local = match.getLocal();
            local.setPuntos(local.getPuntos() + 3);
            teamRepository.save(local);
        } else if (setsGanadosVisitante > setsGanadosLocal) {
            Team visitante = match.getVisitor();
            visitante.setPuntos(visitante.getPuntos() + 3);
            teamRepository.save(visitante);
        }
    }
}

