package com.padelmons.PadelMons.Dto;

import com.padelmons.PadelMons.entities.Categoria;

import java.util.List;

public class TeamDTO {
    public Long id;
    public String name;
    public int puntos;

    public Long categoriaId;
    public List<Long> playerIds;
}
