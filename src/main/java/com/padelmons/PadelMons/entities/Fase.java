package com.padelmons.PadelMons.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fase {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private int orden;

    @ManyToOne
    private Temporada temporada;

    @OneToMany(mappedBy = "fase")
    private List<Jornada> jornadas;

    @ManyToMany
    private List<Team> teams;
    public Fase() {}
    public Fase(String nombre, int orden, Temporada temporada) {
        this.nombre = nombre;
        this.orden = orden;
        this.temporada = temporada;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
