package com.padelmons.PadelMons.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonManagedReference("team-player")
    private List<Player> players = new ArrayList<>();

    private int puntos;
    private int partidosJugados;
    private int victorias;
    private int derrotas;
    private int noJugado;
    private int noPresentado;
    private int mediaSets;
    private int mediaJuegos;

    @ManyToOne
    private Categoria categoria;

    public Team() {}
    public Team(String name) {
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getNoJugado() {
        return noJugado;
    }

    public void setNoJugado(int noJugado) {
        this.noJugado = noJugado;
    }

    public int getNoPresentado() {
        return noPresentado;
    }

    public void setNoPresentado(int noPresentado) {
        this.noPresentado = noPresentado;
    }

    public int getMediaSets() {
        return mediaSets;
    }

    public void setMediaSets(int mediaSets) {
        this.mediaSets = mediaSets;
    }

    public int getMediaJuegos() {
        return mediaJuegos;
    }

    public void setMediaJuegos(int mediaJuegos) {
        this.mediaJuegos = mediaJuegos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
