package br.edu.ifpb.hefastos_android.entities;

/**
 * Created by Henrique M. on 20/03/2017.
 */

public class Prova {
    private QuestaoAberta[] questoesAbertas;
    private QuestaoFechada[] questoesFechadas;
    private int cod_P;
    private int num = 0; // numero de questoes que a prova vai ter
    private int n, nA, nF = 0;

    public Prova() {
    }

    public QuestaoAberta[] getQuestoesAbertas() {
        return questoesAbertas;
    }

    public void setQuestoesAbertas(QuestaoAberta[] questoesAbertas) {
        this.questoesAbertas = questoesAbertas;
    }

    public QuestaoFechada[] getQuestoesFechadas() {
        return questoesFechadas;
    }

    public void setQuestoesFechadas(QuestaoFechada[] questoesFechadas) {
        this.questoesFechadas = questoesFechadas;
    }

    public int getCod_P() {
        return cod_P;
    }

    public void setCod_P(int cod_P) {
        this.cod_P = cod_P;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getnA() {
        return nA;
    }

    public void setnA(int nA) {
        this.nA = nA;
    }

    public int getnF() {
        return nF;
    }

    public void setnF(int nF) {
        this.nF = nF;
    }
}
