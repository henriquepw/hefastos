package br.edu.ifpb.hefastos_android.entities;

import java.util.List;

/**
 * Created by henri on 20/03/2017.
 */

public class QuestaoFechada extends Questao {
    private List<Alternativa> alternativas;

    public QuestaoFechada(String fonte, String descricao, String enunciado) {
        super(fonte, descricao, enunciado);
    }

    @Override
    public String toString() {
        return "(" + this.fonte + ") " + this.enunciado;
    }
}
