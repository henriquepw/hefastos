package br.edu.ifpb.hefastos_android.activities.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQuestaoAbertaActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.btCancelar)
    protected Button btCancelar;

    @BindView(R.id.btSalvar)
    protected Button btSalvar;

    @BindView(R.id.spDisciplina)
    protected MaterialBetterSpinner spDisciplina;

    @BindView(R.id.spAssunto)
    protected MaterialBetterSpinner spAssunto;

    @BindView(R.id.edFonte)
    protected EditText edFonte;

    @BindView(R.id.edEnunciado)
    protected EditText edEnunciado;

    @BindView(R.id.edDescricao)
    protected EditText edDescricao;

    @BindView(R.id.edResposta)
    protected EditText edResposta;

    private Intent intent;

    String[] SPINNERLIST = {"Disciplinas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questao_aberta);

        ButterKnife.bind(this);

        toolbar.setTitle("Nova questão aberta");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);


        spDisciplina.setAdapter(arrayAdapter);
        spAssunto.setAdapter(arrayAdapter);
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
        // se todos os campo n forem nullos
        // Salvar Questão aberta
        // toast de aviso se deu certo ou não
        // depois volta pra main
        startActivity(new Intent(this, MainActivity.class));
        finish();
        // se não toast de "nenhum campo podem ser nulos"
    }
}
