package br.edu.ifpb.hefastos_android.listeners;

import android.view.View;
import android.widget.AdapterView;

import br.edu.ifpb.hefastos_android.activities.delete.DeleteAssuntoActivity;

/**
 * Created by Gabriel on 09/04/2017.
 */

public class DisciplinaOnItemSelectedListener implements AdapterView.OnItemClickListener {
    DeleteAssuntoActivity deleteAssuntoActivity;

    public DisciplinaOnItemSelectedListener(DeleteAssuntoActivity deleteAssuntoActivity) {
        this.deleteAssuntoActivity = deleteAssuntoActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        deleteAssuntoActivity.setDisciplina(deleteAssuntoActivity.getDisciplinas().get(position));
        deleteAssuntoActivity.listarAssuntos(deleteAssuntoActivity.getDisciplina().getId());
    }
}
