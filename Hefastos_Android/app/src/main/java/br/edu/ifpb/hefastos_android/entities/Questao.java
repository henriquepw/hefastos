package br.edu.ifpb.hefastos_android.entities;

/**
 * Created by henri on 20/03/2017.
 */

public class Questao {
    protected int Id_Questao;
    protected int Id_Assunto;
    protected String fonte;
    protected String descricao;
    protected String enunciado;

    public Questao(String fonte, String descricao, String enunciado) {
        this.fonte = fonte;
        this.descricao = descricao;
        this.enunciado = enunciado;
    }

    public Questao() {
    }

    public int getId() {
        return Id_Questao;
    }

    public void setId(int id_Questao) {
        Id_Questao = id_Questao;
    }

    public int getId_Assunto() {
        return Id_Assunto;
    }

    public void setId_Assunto(int Id_Assunto) {
        Id_Assunto = Id_Assunto;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public String toString() {
        return "Questao{" +
                ", fonte='" + fonte + '\'' +
                ", enunciado='" + enunciado + '\'' +
                '}';
    }
}
