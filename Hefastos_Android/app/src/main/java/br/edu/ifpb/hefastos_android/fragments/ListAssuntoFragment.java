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
import br.edu.ifpb.hefastos_android.adapters.AssuntoAdapter;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.entities.Assunto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 14/04/2017.
 */

public class ListAssuntoFragment extends Fragment {
    private MainActivity mainActivity;
    private int id;
    private String disciplina;
    private List<Assunto> assuntos;
    private RecyclerView recyclerView;

    public ListAssuntoFragment(MainActivity m, int id, String disciplina) {
        this.mainActivity = m;
        this.id = id;
        this.disciplina = disciplina;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Assuntos");

        assuntos = new ArrayList<>();

        recyclerView = (RecyclerView) mainActivity.findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        listarAssuntos(this.id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    public void listarAssuntos(int id) {

        Call<List<Assunto>> call = ConnectionServer.getInstance().getService().getAssuntos(id);

        call.enqueue(new Callback<List<Assunto>>() {
            @Override
            public void onResponse(Call<List<Assunto>> call, Response<List<Assunto>> response) {
                try {
                    assuntos = new ArrayList<>();

                    List<Assunto> responseDisc = response.body();
                    assuntos.addAll(responseDisc);
                    setAdapter(assuntos);
                    Log.i("Assuntos", assuntos.toString());

                } catch (Exception e) {
                    Log.e("onResponse", "erro");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Assunto>> call, Throwable t) {
                Log.e("onFailure", "erro");
            }
        });
    }

    public void setAdapter(List<Assunto> a) {
        if (a.size() > 0){
            AssuntoAdapter adapter = new AssuntoAdapter(mainActivity, assuntos, this.disciplina);
            recyclerView.setAdapter(adapter);
        } else {
            mainActivity.noItemfragmet();
        }
    }
}