package com.padelmons.PadelMons.entities;

import jakarta.persistence.*;

@Entity
public class SetMatch {
    @Id
    @GeneratedValue
    private Long id;
    private int numSet;
    private int gameLocal;
    private int gameVisit;
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    public SetMatch() {}

    public SetMatch(int numSet, int gameLocal, int gameVisit, Match match) {
        this.numSet = numSet;
        this.gameLocal = gameLocal;
        this.gameVisit = gameVisit;
        this.match = match;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumSet() {
        return numSet;
    }

    public void setNumSet(int numSet) {
        this.numSet = numSet;
    }

    public int getGameLocal() {
        return gameLocal;
    }

    public void setGameLocal(int gameLocal) {
        this.gameLocal = gameLocal;
    }

    public int getGameVisit() {
        return gameVisit;
    }

    public void setGameVisit(int gameVisit) {
        this.gameVisit = gameVisit;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
