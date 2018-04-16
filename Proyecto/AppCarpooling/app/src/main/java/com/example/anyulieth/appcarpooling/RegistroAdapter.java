package com.example.anyulieth.appcarpooling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anyulieth on 15/04/2018.
 */

public class RegistroAdapter  extends RecyclerView.Adapter<RegistroAdapter.ViewHolder>
        implements View.OnClickListener {
    private ArrayList<Registro> registro;
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

    public RegistroAdapter() {
        registro = new ArrayList<>();
    }

    public void setmDataSet(ArrayList<Registro> registro) {
        this.registro = registro;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_registro, parent, false);
        view.setOnClickListener(this);
        return new RegistroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCedula.setText(registro.get(position).getCedula());
        holder.txtNombre.setText(registro.get(position).getNombre());
        holder.txtApellido1.setText(registro.get(position).getApellido1());
        holder.txtApellido2.setText(registro.get(position).getApellido2());
        holder.txtNacionalidad.setText(registro.get(position).getNacionalidad());
        holder.txtCorreo.setText(registro.get(position).getCorreo());
        holder.txtClave.setText(registro.get(position).getClave());
    }

    @Override
    public int getItemCount() {
        return registro.size();
    }

      public static class ViewHolder extends RecyclerView.ViewHolder {

        public EditText txtCedula;
        public EditText txtNombre;
        public EditText txtApellido1;
        public EditText txtApellido2;
        public EditText txtNacionalidad;
        public EditText txtCorreo;
        public EditText txtClave;

      public ViewHolder(View itemView) {
            super(itemView);            //FALTA CREAR EN ACTIVITY_REGISTRO
            txtCedula = (EditText) itemView.findViewById(R.id.txtCedula);
            txtNombre = (EditText) itemView.findViewById(R.id.txtNombre);
            txtApellido1 = (EditText) itemView.findViewById(R.id.txtApellido1);
            txtApellido2 = (EditText) itemView.findViewById(R.id.txtApellido2);
            txtNacionalidad = (EditText) itemView.findViewById(R.id.txtNacionalidad);
            txtCorreo = (EditText) itemView.findViewById(R.id.txtCorreo);
            txtClave = (EditText) itemView.findViewById(R.id.txtClave);
        }
    }
}
