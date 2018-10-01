package br.edu.ifpb.hefastos_android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.conection.Requisition;
import br.edu.ifpb.hefastos_android.entities.Assunto;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Diego A. Gama on 13/04/2017.
 */

public class AssuntoAdapter extends RecyclerView.Adapter<AssuntoAdapter.ViewHolder> {

    private List<Assunto> assuntos;
    private LayoutInflater inflater;
    private String disciplina;

    public AssuntoAdapter(Context context, List<Assunto> assuntos, String disciplina) {
        inflater = LayoutInflater.from(context);
        this.assuntos = assuntos;
        this.disciplina = disciplina;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_assunto, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Assunto assunto = assuntos.get(position);
        viewHolder.setDate(assunto, position);

    }

    @Override
    public int getItemCount() {
        return assuntos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAssunto;
        private TextView tvDisciplina;
        private TextView tvImage;
        private CircleImageView image;
        private int position;
        private Assunto assunto;

        public ViewHolder(View v) {
            super(v);
            tvAssunto = (TextView) v.findViewById(R.id.tvAssunto);
            tvDisciplina = (TextView) v.findViewById(R.id.tvDisciplina);
            tvImage = (TextView) v.findViewById(R.id.tvImage);
            image = (CircleImageView) v.findViewById(R.id.Image);

            tvDisciplina.setText(disciplina);
            image.setImageResource(Requisition.randomColor());
        }

        public void setDate(Assunto assunto, int position) {
            this.tvAssunto.setText(assunto.getNome());
            this.tvImage.setText(String.valueOf(assunto.getNome().toString().charAt(0)));
            this.assunto = assunto;
            this.position = position;
        }

    }

    public void add(int position, Assunto item) {
        assuntos.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Assunto item) {
        int position = assuntos.indexOf(item);
        assuntos.remove(position);
        notifyItemRemoved(position);
    }

}
