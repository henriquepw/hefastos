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

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDisciplinaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.btCancelar)
    protected Button btCancelar;

    @BindView(R.id.btDeletar)
    protected Button btDeletar;

    @BindView(R.id.spDisciplina)
    protected MaterialBetterSpinner spDisciplina;

    private Intent intent;

    List<Disciplina> disciplinas = new ArrayList<>();
    Disciplina disciplina;

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_disciplina);

        ButterKnife.bind(this);

        toolbar.setTitle("Deletar Disciplina");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        spDisciplina.setOnItemClickListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spDisciplina.setAdapter(arrayAdapter);

        listarDisciplinas();
        //setDisciplinas(Requisition.getRequisition().getDisciplinas());
        //setAdapter(disciplinas);

        Log.i("DeleteDisciplinaActivit", this.disciplinas.toString());


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
    public void btCancelarOnClick(Button b) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btDeletar)
    public void btDeletarOnClick(Button b) {
        // Deletar no servidor
        deletarDisciplina(disciplina);

    }

    public void deletarDisciplina(final Disciplina d) {

        Call<Void> call = ConnectionServer.getInstance().getService().deleteDisciplina(d);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getBaseContext(), "Deletado com sucesso!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DeleteDisciplinaActivity.this, MainActivity.class);
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


    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setAdapter(List<Disciplina> d) {

        ArrayAdapter<Disciplina> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, d);
        spDisciplina.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        disciplina = disciplinas.get(position);
    }
}
