package com.padelmons.PadelMons.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Temporada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "temporada", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference("temporada-fase")
    private List<Fase> fases;

    public Temporada() {}
    public Temporada(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Fase> getFases() {
        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }
}
