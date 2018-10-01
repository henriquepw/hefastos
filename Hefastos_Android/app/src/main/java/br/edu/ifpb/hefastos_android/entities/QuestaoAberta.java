package br.edu.ifpb.hefastos_android.entities;

/**
 * Created by henri on 20/03/2017.
 */

public class QuestaoAberta extends Questao {
    private String resposta;

    public QuestaoAberta(String fonte, String descricao, String enunciado, String resposta) {
        super(fonte, descricao, enunciado);
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "(" + this.fonte + ") " + this.enunciado;
    }
}
