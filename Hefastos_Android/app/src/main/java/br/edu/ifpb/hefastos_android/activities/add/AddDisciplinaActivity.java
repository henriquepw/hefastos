package br.edu.ifpb.hefastos_android.activities.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.entities.Disciplina;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDisciplinaActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.edDisciplina)
    protected EditText edDisciplina;

    @BindView(R.id.btCancelar)
    protected Button btCancelar;

    @BindView(R.id.btSalvar)
    protected Button btSalvar;

    private Intent intent;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disciplina);

        ButterKnife.bind(this);

        toolbar.setTitle("Nova Disciplina");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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
        if ((edDisciplina.getText() != null) || (edDisciplina.getText().toString() != "")) {
            disciplina = new Disciplina(edDisciplina.getText().toString());
            inserirDisciplina();
        } else {
            Toast.makeText(getBaseContext(), "Digite um nome para a disciplina", Toast.LENGTH_LONG).show();
        }
    }

    public void inserirDisciplina() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<Disciplina> call = ConnectionServer.getInstance().getService().inserirDisciplina(disciplina);

                call.enqueue(new Callback<Disciplina>() {
                    @Override
                    public void onResponse(Call<Disciplina> call, Response<Disciplina> response) {
                        Toast.makeText(getBaseContext(), "Salvo com sucesso!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(AddDisciplinaActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Disciplina> call, Throwable t) {

                        Toast.makeText(getBaseContext(), "Erro ao salvar", Toast.LENGTH_LONG).show();
                        Log.e("inserirDisciplina", "erro" + t.getMessage());
                    }
                });
            }
        }).start();
    }
}
