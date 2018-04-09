package com.example.thanatos.examen1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cursos extends AppCompatActivity {
    private Button btnGuardar;
    DataBaseHelper helper = new DataBaseHelper(this);

    ListView lista;
    List<String> item=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        lista= (ListView)findViewById(R.id.lisCursos);
        mostrarCursos();

        btnGuardar= (Button) findViewById(R.id.btnCurso);
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String txtNombreCurso = ((EditText) findViewById(R.id.txtCurso)).getText().toString();


                if (txtNombreCurso.equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios en blanco", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean comprobante = helper.ingresarCurso(txtNombreCurso);
                    if (comprobante) {
                        Curso curso = new Curso();
                        curso.setNombreCurso(txtNombreCurso);
                        Intent exito = new Intent(Cursos.this, Inicio.class);
                        exito.putExtra("Usuario", txtNombreCurso);
                        startActivity(exito);
                    } else {
                        Toast error = Toast.makeText(Cursos.this, "Ocurrio un error", Toast.LENGTH_SHORT);
                        error.show();
                    }
                }
            }
        });
    }

    private void mostrarCursos(){
        Cursor c = helper.getCursor();
        item = new ArrayList<String>();
        String nombre="";
        if(c.moveToFirst()){
            do{
                nombre= c.getString(1);
                item.add(nombre);

            }while(c.moveToNext());

        }
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item);
        lista.setAdapter(adaptador);



    }
}
