package br.edu.ifpb.hefastos_android.activities.delete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteQuestaoActivity extends AppCompatActivity {
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

    @BindView(R.id.spQuestao)
    protected MaterialBetterSpinner spQuestao;

    private Intent intent;

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_questao);

        ButterKnife.bind(this);

        toolbar.setTitle("Nova Questão");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        spAssunto.setAdapter(arrayAdapter);
        spDisciplina.setAdapter(arrayAdapter);
        spQuestao.setAdapter(arrayAdapter);
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
    }

}
