package br.edu.ifpb.hefastos_android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.adapters.DisciplinaAdapter;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Henrique M. Miranda on 14/04/2017.
 */

public class ListDisciplinasFragment extends Fragment {
    private MainActivity mainActivity;

    private List<Disciplina> disciplinas = new ArrayList<>();
    private RecyclerView recyclerView;

    public ListDisciplinasFragment(MainActivity m){
        this.mainActivity = m;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Disciplinas");

        listarDisciplinas();
        recyclerView = (RecyclerView) mainActivity.findViewById(R.id.recycler);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    public void listarDisciplinas() {

        Call<List<Disciplina>> call = ConnectionServer.getInstance().getService().getAllDisciplina();

        call.enqueue(new Callback<List<Disciplina>>() {
            @Override
            public void onResponse(Call<List<Disciplina>> call, Response<List<Disciplina>> response) {
                try {
                    disciplinas = new ArrayList<>();

                    List<Disciplina> responseDisc = response.body();
                    disciplinas.addAll(responseDisc);
                    Log.i("Disciplinas", disciplinas.toString());

                    setAdapter(disciplinas);
                } catch (Exception e) {
                    Log.e("onResponse", "erro");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Disciplina>> call, Throwable t) {
                Log.e("onFailure", "erro");
            }
        });

    }

    public void setAdapter(List<Disciplina> disciplinas){
        if(disciplinas.size() > 0){
            DisciplinaAdapter adapter = new DisciplinaAdapter(mainActivity, disciplinas);
            recyclerView.setAdapter(adapter);
        } else {
            mainActivity.noItemfragmet();
        }

    }
}
