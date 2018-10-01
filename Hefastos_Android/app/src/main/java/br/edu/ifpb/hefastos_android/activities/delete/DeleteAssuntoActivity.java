package br.edu.ifpb.hefastos_android.activities.delete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.entities.Assunto;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import br.edu.ifpb.hefastos_android.listeners.DisciplinaOnItemSelectedListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class DeleteAssuntoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.btCancelar)
    protected Button btCancelar;

    @BindView(R.id.btDeletar)
    protected Button btDeletar;

    @BindView(R.id.spAssunto)
    protected MaterialBetterSpinner spAssunto;

    @BindView(R.id.spDisciplina)
    protected MaterialBetterSpinner spDisciplina;

    private List<Disciplina> disciplinas;
    private List<Assunto> assuntos;

    private Disciplina disciplina;
    private Assunto assunto;

    private Intent intent;

    String[] SPINNERLIST = {"Selecione uma Disciplina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_assunto);

        ButterKnife.bind(this);

        toolbar.setTitle("Deletar Assunto");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        spAssunto.setOnItemClickListener(this);
        spDisciplina.setOnItemClickListener(new DisciplinaOnItemSelectedListener(this));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spAssunto.setAdapter(arrayAdapter);
        spDisciplina.setAdapter(arrayAdapter);

        listarDisciplinas();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btCancelar)
    public void btCancelaronClick(Button b) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btDeletar)
    public void onClick(Button b) {
        // Deletar no servidor
        deletarAssunto(assunto);
    }

    public void listarDisciplinas() {
        new Thread(new Runnable() {
            @Override
            public void run() {

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
        }).start();

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
                    setAdapterAssunto(assuntos);
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

    public void deletarAssunto(Assunto a) {

        a.setDisciplina(null);

        Call<Void> call = ConnectionServer.getInstance().getService().deleteAssunto(a);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getBaseContext(), "Deletado com sucesso!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DeleteAssuntoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao deletar", Toast.LENGTH_LONG).show();
                Log.e("inserirDisciplina", "erro" + t.getMessage());
            }
        });
    }

    public void setAdapter(List<Disciplina> d) {

        ArrayAdapter<Disciplina> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, d);
        spDisciplina.setAdapter(adapter);
    }

    public void setAdapterAssunto(List<Assunto> a) {

        ArrayAdapter<Assunto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, a);
        spAssunto.setAdapter(adapter);
    }


    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Disciplina getDisciplina() {

        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        assunto = assuntos.get(position);
    }


}

