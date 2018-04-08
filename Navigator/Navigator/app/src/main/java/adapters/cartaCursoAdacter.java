package adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johan.navigator.MainActivity;
import com.example.johan.navigator.R;
import com.example.johan.navigator.frmDetalle;

import java.util.List;

import db.DataBaseManagerCompras;
import model.compra;
import model.curso;

/**
 * Created by Johan on 19/03/2017.
 */

public class cartaCursoAdacter extends RecyclerView.Adapter<cartaCursoAdacter.CartaViewHolder> {

     private final Context mainContext;
     private final List<compra> items;

     private DataBaseManagerCompras managercompra;
     private RecyclerView recycler;

    public cartaCursoAdacter(Context mainContext, List<compra> items) {
        this.mainContext = mainContext;
        this.items = items;
    }



    static class CartaViewHolder extends RecyclerView.ViewHolder{
        protected TextView id;
        protected TextView descripcion;
        protected TextView total;


        public CartaViewHolder(View v) {
            super(v);

            this.id = (TextView)v.findViewById(R.id.lblID);
            this.descripcion = (TextView)v.findViewById(R.id.lbldescripcion);
            this.total = (TextView)v.findViewById(R.id.lblTotal);
        }
    }

    @Override
    public CartaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_curso, viewGroup, false);
        return new CartaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CartaViewHolder viewholder,final int position) {

            compra item = items.get(position);
            viewholder.itemView.setTag(item);

            viewholder.id.setText("Numero Factura :"+ item.getId());
            viewholder.descripcion.setText("Nombre :"+ item.getDescripcion());
            viewholder.total.setText("Total :"+ item.getTotal());

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compra item = items.get(position);
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(mainContext);
                View mView = LayoutInflater.from(mainContext).inflate(R.layout.dialog_total,null, false);


                managercompra = new DataBaseManagerCompras(mainContext);
                TextView lblTOTAL=(TextView)mView.findViewById(R.id.monto);
                lblTOTAL.setText("El total de la compra es: "+String.valueOf(managercompra.getTotalCompra(item.getId())));

              /*  final EditText mId=(EditText)mView.findViewById(R.id.editTextID);
                final EditText mNombre=(EditText)mView.findViewById(R.id.editTextNombre);
                final EditText mDescrip=(EditText)mView.findViewById(R.id.editTextDescrip);
                final EditText mPrecio=(EditText)mView.findViewById(R.id.editTextprecio);


                final Button mAgregar=(Button)mView.findViewById(R.id.btnAgregarDetalle);

             /* final Button mUpdate=(Button)mView.findViewById(R.id.btnActu);
              final Button mDelete=(Button)mView.findViewById(R.id.btnEli);*/
              //  mId.setText(item.getId());
              /*mDescrip.setText(item.getDescripcion());
              mPrecio.setText(String.valueOf(item.getTotal()));*/

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show( );





               /* mAgregar.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    if(mId.getText().toString().isEmpty())
                                                    {

                                                    }
                                                    else if (mDescrip.getText().toString().isEmpty())
                                                    {
                                                        Toast.makeText(mainContext,"Completar el campo Descripción",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if(mPrecio.getText().toString().isEmpty())
                                                    {
                                                        Toast.makeText(mainContext,"Completar el campo Precio",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if(!mId.getText().toString().isEmpty()&&!mDescrip.getText().toString().isEmpty()&&!mPrecio.getText().toString().isEmpty()){

                                                        Toast.makeText(mainContext,"Agregado con éxitoso",Toast.LENGTH_SHORT).show();

                                                        managercompra = new DataBaseManagerCompras(mainContext);

                                                        managercompra.insert_detalle(null,mId.getText().toString(),mDescrip.getText().toString(),mPrecio.getText().toString());
                                                        managercompra.update_total(mId.getText().toString(),mPrecio.getText().toString());
                                                        dialog.dismiss();
                                                    }

                                                }
                                            }
                );*/

            }
        });

        viewholder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override public boolean onLongClick(View v) {
              compra item = items.get(position);

              Intent intent = new Intent(mainContext,frmDetalle.class);
              intent.putExtra("idCompra",String.valueOf(item.getId()));
              intent.putExtra("titulocompra",String.valueOf(item.getDescripcion()));
              v.getContext().startActivity(intent);
              return false;
             }
          });

    }
    /*private void  recargarRecycler(){

        items = managercurso.getCursosList();
        adapter= new cartaCursoAdacter(this,listarItemsCurso);
        recycler.setAdapter(adapter);
    }*/
    @Override
    public int getItemCount() {

        return items.size();
    }
}
