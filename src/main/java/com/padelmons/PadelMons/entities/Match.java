package com.padelmons.PadelMons.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "local_team_id")
    private Team local;
    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    private Team visitor;
    @ManyToOne
    @JoinColumn(name = "jornada_id")
    @JsonBackReference("jornada-match")
    private Jornada jornada;
    @OneToMany (mappedBy = "match")
    private List<SetMatch> sets;
    private boolean finalizado;
    private LocalDateTime fecha;
    public Match() {}
    public Match(Team local, Team visitor, Jornada jornada) {
        this.local = local;
        this.visitor = visitor;
        this.jornada = jornada;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getLocal() {
        return local;
    }

    public void setLocal(Team local) {
        this.local = local;
    }

    public Team getVisitor() {
        return visitor;
    }

    public void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public List<SetMatch> getSets() {
        return sets;
    }

    public void setSets(List<SetMatch> sets) {
        this.sets = sets;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
