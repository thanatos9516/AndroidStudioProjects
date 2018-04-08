package adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johan.navigator.R;

import java.util.List;

import model.compra;
import model.detalle;

/**
 * Created by Johan on 21/04/2017.
 */

public class cartaDetalleAdacter extends RecyclerView.Adapter<cartaDetalleAdacter.CartaViewHolder> {

    private final Context mainContext;
    private final List<detalle> items;

    public cartaDetalleAdacter(Context mainContext, List<detalle> items) {
        this.mainContext = mainContext;
        this.items = items;
    }



    static class CartaViewHolder extends RecyclerView.ViewHolder{
        protected TextView id;
        protected TextView idcompras;
        protected TextView descripcion;
        protected TextView precio;


        public CartaViewHolder(View v) {
            super(v);

            this.id = (TextView)v.findViewById(R.id.lblID);
            this.idcompras = (TextView)v.findViewById(R.id.lbIDcompra);
            this.descripcion = (TextView)v.findViewById(R.id.lbldescripcion);
            this.precio = (TextView)v.findViewById(R.id.lblTotal);
        }
    }

    @Override
    public cartaDetalleAdacter.CartaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_detalle, viewGroup, false);
        return new cartaDetalleAdacter.CartaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(cartaDetalleAdacter.CartaViewHolder viewholder,final int position) {

        detalle item = items.get(position);
        viewholder.itemView.setTag(item);

        viewholder.id.setText("Detalle :"+ item.getId());
        viewholder.idcompras.setText("Factura:"+ item.getIdCompra());
        viewholder.descripcion.setText("Nombre :"+ item.getDescripcion());
        viewholder.precio.setText("Precio :"+ item.getPrecio());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}