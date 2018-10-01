package br.edu.ifpb.hefastos_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.edu.ifpb.hefastos_android.activities.MainActivity;
import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.conection.Requisition;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Diego A. Gama on 13/04/2017.
 */

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.ViewHolder> {

    private List<Disciplina> disciplinas;
    private LayoutInflater inflater;
    private MainActivity context;

    public DisciplinaAdapter(MainActivity context, List<Disciplina> disciplinas) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.disciplinas = disciplinas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_disciplina, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Disciplina disciplina = disciplinas.get(position);
        viewHolder.setDate(disciplina, position);

        ViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.assuntofragmet(disciplina.getId(), disciplina.getNome());
            }
        });
    }

    @Override
    public int getItemCount() {
        return disciplinas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static TextView tvDisciplina;
        private TextView tvImage;
        private CircleImageView image;
        private int position;
        private Disciplina disciplina;

        public ViewHolder(View v) {
            super(v);
            tvDisciplina = (TextView) v.findViewById(R.id.tvDisciplina);
            tvImage = (TextView) v.findViewById(R.id.tvImage);
            image = (CircleImageView) v.findViewById(R.id.Image);

            image.setImageResource(Requisition.randomColor());
        }

        public void setDate(Disciplina disciplina, int position) {
            this.tvDisciplina.setText(disciplina.getNome());
            this.tvImage.setText(String.valueOf(disciplina.getNome().toString().charAt(0)));
            this.disciplina = disciplina;
            this.position = position;
        }

        public static void setOnClickListener(View.OnClickListener onClickListener) {
            tvDisciplina.setOnClickListener(onClickListener);
        }

    }

    public void add(int position, Disciplina item) {
        disciplinas.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Disciplina item) {
        int position = disciplinas.indexOf(item);
        disciplinas.remove(position);
        notifyItemRemoved(position);
    }


}
