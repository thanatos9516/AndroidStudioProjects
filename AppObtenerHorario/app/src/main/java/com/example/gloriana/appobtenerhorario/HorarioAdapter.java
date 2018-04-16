package com.example.gloriana.appobtenerhorario;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;


/**
 * Created by maste on 25/02/2018.
 */

public class HorarioAdapter
        extends RecyclerView.Adapter<HorarioAdapter.ViewHolder>
        implements View.OnClickListener {

    private ArrayList<Horario> mDataSet;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if (listener != null) {
            listener.onClick(view);
        }
    }


    public HorarioAdapter() {
        mDataSet = new ArrayList<>();
    }

    public void setmDataSet(ArrayList<Horario> mDataSet) {
        this.mDataSet = mDataSet;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_horario, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.lblCodigo.setText(mDataSet.get(position).getCodigo());
        holder.lblCurso.setText(mDataSet.get(position).getCurso());
        holder.lblHorario.setText(mDataSet.get(position).getHorario());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView lblCodigo;
        public TextView lblCurso;
        public TextView lblHorario;

        public ViewHolder(View itemView) {
            super(itemView);
            lblCodigo = (TextView) itemView.findViewById(R.id.lblCodigo);
            lblCurso = (TextView) itemView.findViewById(R.id.lblCurso);
            lblHorario = (TextView) itemView.findViewById(R.id.lblHorario);
        }
    }
}
