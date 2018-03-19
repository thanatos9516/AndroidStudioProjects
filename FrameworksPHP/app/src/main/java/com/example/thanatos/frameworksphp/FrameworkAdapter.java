package com.example.thanatos.frameworksphp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Thanatos on 5/3/2018.
 */

public class FrameworkAdapter extends RecyclerView.Adapter<FrameworkAdapter.FrameworkViewHolder>
{

    private List<Framework> items;

    public FrameworkAdapter(List<Framework> items) {
        this.items = items;
    }

    @Override
    public
    FrameworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.framework_cardview, parent, false
                );
        return new
                FrameworkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FrameworkViewHolder holder, int position) {
        holder.imagen.setImageResource(items.get(position).getImagen());
        holder.nombre.setText(items.get(position).getNombre());
        holder.visitas.setText("Visitas:" +
                                String.valueOf(items.get(position).getValoracion()));
    }

    @Override
    public int
    getItemCount() {
        return items.size();
    }

    public static class FrameworkViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;
        public
        FrameworkViewHolder(View itemView)
        {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            visitas = (TextView) itemView.findViewById(R.id.visitas);
        }
    }
}
