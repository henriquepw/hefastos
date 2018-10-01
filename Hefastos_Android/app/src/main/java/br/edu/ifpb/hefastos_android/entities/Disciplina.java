package br.edu.ifpb.hefastos_android.entities;

import java.util.List;

/**
 * Created by Henrique M. Miranda on 20/03/2017.
 */

public class Disciplina {
    private String nome;
    private int id;
    private List<Prova> provas;
    private List<Assunto> assuntos;

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public Disciplina() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Prova> getProvas() {
        return provas;
    }

    public void setProvas(List<Prova> provas) {
        this.provas = provas;
    }

    public List<Assunto> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<Assunto> assuntos) {
        this.assuntos = assuntos;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
