package com.padelmons.PadelMons.services;

import com.padelmons.PadelMons.Dto.JornadaDTO;
import com.padelmons.PadelMons.entities.Fase;
import com.padelmons.PadelMons.entities.Jornada;
import com.padelmons.PadelMons.repositories.FaseRepository;
import com.padelmons.PadelMons.repositories.JornadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaService {
    private final JornadaRepository jornadaRepository;
    private final FaseRepository faseRepository;

    public JornadaService(JornadaRepository jornadaRepository, FaseRepository faseRepository) {
        this.jornadaRepository = jornadaRepository;
        this.faseRepository = faseRepository;
    }

    public Jornada crearJornada(JornadaDTO dto) {
        Fase fase = faseRepository.findById(dto.faseId)
                .orElseThrow(() -> new RuntimeException("Fase no encontrada"));

        Jornada jornada = new Jornada();
        jornada.setNumJornada(dto.numJornada);
        jornada.setFase(fase);

        return jornadaRepository.save(jornada);
    }

    public List<Jornada> listarTodas() {
        return jornadaRepository.findAll();
    }

    public Jornada obtenerPorId(Long id) {
        return jornadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));
    }

    public List<Jornada> listarPorFase(Long faseId) {
        return jornadaRepository.findByFaseId(faseId);
    }
}
