package com.padelmons.PadelMons.Dto;
import com.padelmons.PadelMons.Dto.PlayerDTO;
import com.padelmons.PadelMons.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamResponseDTO {

    public Long id;
    public String name;
    public int puntos;
    public Long categoriaId;
    public String categoriaNombre;
    public List<PlayerDTO> players;
    public CategoriaDTO categoria;

    public TeamResponseDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.puntos = team.getPuntos();
        this.categoriaId = team.getCategoria() != null ? team.getCategoria().getId() : null;
        this.categoriaNombre = team.getCategoria() != null ? team.getCategoria().getNombre() : null;
        this.players = team.getPlayers().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }



}
