package com.vinicius.SpringEventos.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @NotEmpty(message = "Informe o nome")
    private String rg;

    @NotEmpty(message = "Informe o nome")
    private String nome;

    @ManyToOne
    private Evento evento;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
