package br.edu.ifpb.hefastos_android.activities.delete;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import br.edu.ifpb.hefastos_android.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.btDisciplina)
    protected Button btDisciplina;

    @BindView(R.id.btAssunto)
    protected Button btAssunto;

    /*
    @BindView(R.id.btQuestao)
    protected Button btQuestao;
    */

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha);

        ButterKnife.bind(this);

        toolbar.setTitle("Deletar");
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

    @OnClick({R.id.btAssunto, R.id.btDisciplina, /*R.id.btQuestao*/})
    public void escolhaOnClick(Button b) {

        switch (b.getId()) {
            case R.id.btAssunto:
                intent = new Intent(this, DeleteAssuntoActivity.class);
                startActivity(intent);
                break;
            case R.id.btDisciplina:
                intent = new Intent(this, DeleteDisciplinaActivity.class);
                startActivity(intent);
                break;
            /*
            case R.id.btQuestao:
                intent = new Intent(this, DeleteQuestaoActivity.class);
                startActivity(intent);
                break;
                */
        }
    }
}
