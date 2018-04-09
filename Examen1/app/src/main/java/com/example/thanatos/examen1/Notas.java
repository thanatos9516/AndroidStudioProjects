package com.example.thanatos.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Notas extends AppCompatActivity {
    DataBaseHelper helper = new DataBaseHelper(this);
    ListView listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        Intent in = getIntent();
        String estudiante = in.getStringExtra(("Estudiante"));

        ListAdapter adaptador = new ArrayAdapter<Calificacion>(this, android.R.layout.simple_list_item_1, helper.listaNotas(Integer.parseInt(estudiante)));
//Asociamos el adaptador a la vista.
        Toast.makeText(Notas.this,String.valueOf(estudiante),
                Toast.LENGTH_LONG).show();
        ListView lv = (ListView) findViewById(R.id.listaCalificaciones);
        lv.setAdapter(adaptador);
    }
}
