package com.example.thanatos.examen1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Thanatos on 8/4/2018.
 */

public class List<S> extends AppCompatActivity {


    ListView lista;
    // SQLite_OpenHelper helper= new SQLite_OpenHelper(getApplicationContext());
    java.util.List<String> item=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lista= (ListView)findViewById(R.id.listEstudiantes1);
        mostrarEstudiantes();
        registerForContextMenu(lista);
    }

    private void mostrarEstudiantes(){
        SQLite_OpenHelper helper= new SQLite_OpenHelper(this);
        Cursor c= helper.getEstudiante();
        item = new ArrayList<String>();
        int id;
        String nombre="";
        if(c.moveToFirst()){
            do{
                id= c.getInt(0);
                nombre= c.getString(1);
                item.add(id+ ""+nombre);

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
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ver_cursos:
                Intent exito = new Intent(List.this, Cursos.class);
                startActivity(exito);
                return true;
            case R.id.cambiar_clave:
                exito = new Intent(List.this, Clave.class);
                startActivity(exito);
                return true;
            case R.id.salir:
                exito = new Intent(List.this, Login.class);
                startActivity(exito);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accion_ver:
                Intent exito = new Intent(List.this, Notas.class);
                startActivity(exito);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
