package br.edu.ifpb.hefastos_android.activities.add;

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
import android.widget.EditText;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.entities.Assunto;
import br.edu.ifpb.hefastos_android.entities.Disciplina;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAssuntoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.edAssunto)
    protected EditText edAssunto;

    @BindView(R.id.spDisciplina)
    protected MaterialBetterSpinner spDisciplina;

    @BindView(R.id.btCancelar)
    protected Button btCancelar;

    @BindView(R.id.btSalvar)
    protected Button btSalvar;

    private List<Disciplina> disciplinas;
    private Intent intent;
    private Assunto assunto;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assunto);

        ButterKnife.bind(this);

        toolbar.setTitle("Novo Assunto");

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        listarDisciplinas();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, disciplinas);
        spDisciplina.setAdapter(adapter);
        spDisciplina.setOnItemClickListener(this);
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

    @OnClick(R.id.btSalvar)
    public void btSalvarOnClick(Button b) {
        assunto = new Assunto(edAssunto.getText().toString());
        assunto.setDisciplina(disciplina);

        inserirAssunto(assunto);
    }

    public void inserirAssunto(Assunto a) {

        Call<Assunto> call = ConnectionServer.getInstance().getService().inserirAssunto(a);

        call.enqueue(new Callback<Assunto>() {
            @Override
            public void onResponse(Call<Assunto> call, Response<Assunto> response) {
                Toast.makeText(getBaseContext(), "Salvo com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddAssuntoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Assunto> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao salvar!", Toast.LENGTH_LONG).show();
                Log.e("inserirAssunto", "erro");
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        disciplina = disciplinas.get(position);
    }

    public void setDisciplinas(List<Disciplina> d) {
        disciplinas = d;
    }

    public void setAdapter(List<Disciplina> d) {
        ArrayAdapter<Disciplina> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, d);
        spDisciplina.setAdapter(adapter);
    }
}
