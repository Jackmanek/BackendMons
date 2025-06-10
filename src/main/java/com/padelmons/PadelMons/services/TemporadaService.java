package com.padelmons.PadelMons.services;

import com.padelmons.PadelMons.entities.Categoria;
import com.padelmons.PadelMons.entities.Fase;
import com.padelmons.PadelMons.entities.Team;
import com.padelmons.PadelMons.repositories.CategoriaRepository;
import com.padelmons.PadelMons.repositories.TeamRepository;
import com.padelmons.PadelMons.repositories.TemporadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TemporadaService {
    private final CategoriaRepository categoriaRepository;
    private final TeamRepository teamRepository;

    public TemporadaService(CategoriaRepository categoriaRepository, TeamRepository teamRepository) {
        this.categoriaRepository = categoriaRepository;
        this.teamRepository = teamRepository;
    }

    public void crearCategoriasParaFases(Fase fase) {
        String[] nombres = {"DH", "2A", "2B", "3A", "3B", "4A", "4B"};

        for (int i = 0; i < nombres.length; i++) {
            Categoria cat = new Categoria(nombres[i], i + 1, fase);
            categoriaRepository.save(cat);
        }
    }

    @Transactional
    public void aplicarAscensoYDescensos(Fase fase) {
        List<Categoria> categorias = categoriaRepository.findByFaseOrderByOrdenAsc(fase);

        for(int i = 0; i < categorias.size(); i++) {
            Categoria actual = categorias.get(i);

            List<Team> teamsOrdenados = actual.getTeams().stream()
                    .sorted(Comparator.comparingInt(Team::getPuntos).reversed())
                    .toList();

            if(i > 0 && teamsOrdenados.size() >=3 ) {
                List<Team> ascendentes = teamsOrdenados.subList(0, 3);
                Categoria categoriaSuperior = categorias.get(i-1);
                ascendentes.forEach(team -> {
                    team.setCategoria(categoriaSuperior);
                    teamRepository.save(team);
                });
            }

            if(i < categorias.size() - 1 && teamsOrdenados.size() >=3 ) {
                List<Team> descendentes = teamsOrdenados.subList(teamsOrdenados.size() - 3, teamsOrdenados.size());
                Categoria categoriaInferior = categorias.get(i+1);
                descendentes.forEach(team -> {
                    team.setCategoria(categoriaInferior);
                    teamRepository.save(team);
                });
            }
        }
    }
}
