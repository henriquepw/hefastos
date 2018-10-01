package br.edu.ifpb.hefastos_android.entities;

/**
 * Created by Henrique M. Miranda on 20/03/2017.
 */

public class Assunto {
    private String nome;
    private int id;
    private Disciplina disciplina;

    public Assunto(String nome) {
        this.nome = nome;
    }

    public Assunto() {
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

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assunto assunto = (Assunto) o;

        if (id != assunto.id) return false;
        if (nome != null ? !nome.equals(assunto.nome) : assunto.nome != null) return false;
        return disciplina != null ? disciplina.equals(assunto.disciplina) : assunto.disciplina == null;

    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (disciplina != null ? disciplina.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
