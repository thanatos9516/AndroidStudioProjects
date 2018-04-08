package com.example.johan.navigator;

import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import adapters.cartaDetalleAdacter;
import db.DataBaseManagerCompras;
import model.detalle;

public class frmDetalle extends AppCompatActivity {

    private RecyclerView recycler;
    private cartaDetalleAdacter adapter;
    private RecyclerView.LayoutManager lManager;
    private List<detalle> listarItemsDetalle;
    private DataBaseManagerCompras managerdetalle;
    private Button mAgregar,mostrar;

    public  String idCompra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_frm_detalle);
        TextView lblTitulo=(TextView) findViewById(R.id.titulo);

        String titulocompra=getIntent().getExtras().getString("titulocompra");
        lblTitulo.setText("Detalles de la Compra:"+titulocompra);
        idCompra = getIntent().getExtras().getString("idCompra");

        managerdetalle = new DataBaseManagerCompras(this);
        listarItemsDetalle = managerdetalle.getDetalleList(idCompra);

        inicializarRecicler();

        mAgregar=(Button)findViewById(R.id.btnInsertarDetalle);


        mAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder= new AlertDialog.Builder(frmDetalle.this);

                View mView = getLayoutInflater().inflate(R.layout.dialog_eliminar,null);
                final EditText mId=(EditText)mView.findViewById(R.id.editTextID);
                final EditText mDescrip=(EditText)mView.findViewById(R.id.editTextDescrip);
                final EditText mPrecio=(EditText)mView.findViewById(R.id.editTextprecio);
                final Button mAgregarDetalle=(Button)mView.findViewById(R.id.btnAgregarDetalle);
                mId.setText(idCompra);
                mBuilder.setView(mView);
               final AlertDialog dialog = mBuilder.create();
                dialog.show();
                mAgregarDetalle.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    if(mId.getText().toString().isEmpty())
                                                    {

                                                    }
                                                    else if (mDescrip.getText().toString().isEmpty())
                                                    {
                                                        Toast.makeText(frmDetalle.this,"Completar el campo Descripción",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if(mPrecio.getText().toString().isEmpty())
                                                    {
                                                        Toast.makeText(frmDetalle.this,"Completar el campo Precio",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if(!mId.getText().toString().isEmpty()&&!mDescrip.getText().toString().isEmpty()&&!mPrecio.getText().toString().isEmpty()){

                                                        Toast.makeText(frmDetalle.this,"Agregado con éxitoso",Toast.LENGTH_SHORT).show();

                                                        managerdetalle = new DataBaseManagerCompras(frmDetalle.this);

                                                        managerdetalle.insert_detalle(null,mId.getText().toString(),mDescrip.getText().toString(),mPrecio.getText().toString());
                                                        managerdetalle.update_total(mId.getText().toString(),mPrecio.getText().toString());
                                                        managerdetalle.update_total(mId.getText().toString(),mPrecio.getText().toString());
                                                        listarItemsDetalle = managerdetalle.getDetalleList(idCompra);
                                                        inicializarRecicler();
                                                        dialog.dismiss();
                                                    }

                                                }
                                            }
                );

            }
        });

    }
    public void inicializarRecicler(){

        recycler=(RecyclerView)findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter= new cartaDetalleAdacter(this,listarItemsDetalle);

        recycler.setAdapter(adapter);

        recycler.setItemAnimator(new DefaultItemAnimator());
    }



}
