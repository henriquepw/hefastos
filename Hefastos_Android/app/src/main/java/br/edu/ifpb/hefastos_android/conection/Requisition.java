package br.edu.ifpb.hefastos_android.conection;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gabriel de Sousa on 14/04/2017.
 */

public class Requisition {
    public List<Disciplina> disciplinas = new ArrayList<>();

    public Requisition() {
    }

    public static Requisition getRequisition() {
        Requisition instance = new Requisition();
        return instance;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        Log.i("setDisciplinas", disciplinas.toString());
        this.disciplinas = disciplinas;
    }

    public List<Disciplina> getDisciplinas() {

        Call<List<Disciplina>> call = ConnectionServer.getInstance().getService().getAllDisciplina();

        call.enqueue(new Callback<List<Disciplina>>() {
            @Override
            public void onResponse(Call<List<Disciplina>> call, Response<List<Disciplina>> response) {
                try {
                    List<Disciplina> disciplinas = new ArrayList<>();

                    List<Disciplina> responseDisc = response.body();
                    disciplinas.addAll(responseDisc);
                    Log.i("Disciplinas", disciplinas.toString());

                } catch (Exception e) {
                    Log.e("onResponse", "erro");
                    e.printStackTrace();
                } finally {
                    setDisciplinas(disciplinas);
                }
            }

            @Override
            public void onFailure(Call<List<Disciplina>> call, Throwable t) {
                Log.e("onFailure", "erro");
            }


        });
        Log.i("this.Disciplinas2", this.disciplinas.toString());
        return this.disciplinas;
    }

    public static int randomColor() {
        int colors[] = {
                R.color.red,
                R.color.pink,
                R.color.purple,
                R.color.deep_purple,
                R.color.indigo,
                R.color.blue,
                R.color.light_blue,
                R.color.cyan,
                R.color.teal,
                R.color.green,
                R.color.light_green,
                R.color.lime,
                R.color.yellow,
                R.color.amber,
                R.color.deep_orange,
                R.color.brown,
                R.color.blue_grey,
        };

        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }
}
