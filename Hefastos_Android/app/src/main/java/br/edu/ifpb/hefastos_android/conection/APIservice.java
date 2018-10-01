package br.edu.ifpb.hefastos_android.conection;

import java.util.List;

import br.edu.ifpb.hefastos_android.entities.Assunto;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import br.edu.ifpb.hefastos_android.entities.QuestaoAberta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Gabriel de Sousa Barros on 07/04/2017.
 */

public interface APIservice {

    /**************
     * Disciplina *
     **************/
    @GET("disciplina/listar")
    Call<List<Disciplina>> getAllDisciplina();

    @POST("disciplina/inserir")
    Call<Disciplina> inserirDisciplina(@Body Disciplina disciplina);

    @POST("disciplina/deletar")
    Call<Void> deleteDisciplina(@Body Disciplina disciplina);

    /***********
     * Assunto *
     ***********/
    @GET("assunto/listar/id_disciplina/{id_disciplina}")
    Call<List<Assunto>> getAssuntos(@Path("id_disciplina") int id_disciplina);

    @POST("assunto/inserir")
    Call<Assunto> inserirAssunto(@Body Assunto assunto);

    @POST("assunto/deletar")
    Call<Void> deleteAssunto(@Body Assunto assunto);

    /***********
     * QuestãoAberta *
     ***********/
    @GET("questao/aberta/listar/id_assunto/{id_assunto}")
    Call<List<QuestaoAberta>> getQuestoesAbertas(@Path("id_assunto") int id_assunto);

    @POST("questao/aberta/inserir")
    Call<QuestaoAberta> inserirQuestaoAberta(@Body QuestaoAberta questao);

    @POST("questao/aberta/deletar")
    Call<Void> deleteQuestaoAberta(@Body QuestaoAberta questao);
}
