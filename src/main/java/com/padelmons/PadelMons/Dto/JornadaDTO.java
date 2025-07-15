package com.padelmons.PadelMons.Dto;

public class JornadaDTO {
    public int numJornada;
    public Long faseId;

    public JornadaDTO(int numJornada, Long faseId) {
        this.numJornada = numJornada;
        this.faseId = faseId;
    }

    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public Long getFaseId() {
        return faseId;
    }

    public void setFaseId(Long faseId) {
        this.faseId = faseId;
    }
}
