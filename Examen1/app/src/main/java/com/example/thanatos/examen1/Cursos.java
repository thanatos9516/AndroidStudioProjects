package com.example.thanatos.examen1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanatos on 8/4/2018.
 */

public class Cursos extends AppCompatActivity {

    ListView lista;
    // SQLite_OpenHelper helper= new SQLite_OpenHelper(getApplicationContext());
    List<String> item=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        lista= (ListView)findViewById(R.id.lisCursos);
        mostrarCursos();
    }

    private void mostrarCursos(){
        SQLite_OpenHelper helper= new SQLite_OpenHelper(this);
        Cursor c= helper.getCursor();
        item = new ArrayList<String>();
        String nombre="";
        if(c.moveToFirst()){
            do{
                nombre= c.getString(1);
                item.add(nombre);

            }while(c.moveToNext());

        }
        ArrayAdapter<String>adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item);
        lista.setAdapter(adaptador);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Estudiantes:
                Intent exito = new Intent(Cursos.this, com.example.thanatos.examen1.List.class);
                startActivity(exito);
                return true;
            case R.id.cambiar_clave:
                exito = new Intent(Cursos.this, Clave.class);
                startActivity(exito);
                return true;
            case R.id.salir:
                exito = new Intent(Cursos.this, Login.class);
                startActivity(exito);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

