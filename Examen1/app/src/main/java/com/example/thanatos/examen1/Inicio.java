package com.example.thanatos.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 13/04/2016.
 */
public class Inicio extends AppCompatActivity {

    private Button btnMenu;
    ListView listaEstudiante;
    DataBaseHelper helper = new DataBaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ListAdapter adaptador = new ArrayAdapter<Estudiante>(this, android.R.layout.simple_list_item_1, helper.listaEstudiante());
//Asociamos el adaptador a la vista.
        ListView lv = (ListView) findViewById(R.id.lstEstudiante);
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
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
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DataBaseHelper helper = new DataBaseHelper(this);
        Estudiante estudiante = helper.listaEstudiante().get(info.position);

        switch (item.getItemId()) {
            case R.id.action_ver:

                Toast.makeText(Inicio.this,String.valueOf(estudiante.getUsuario()),
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, Notas.class);
                i.putExtra("Estudiante",String.valueOf(estudiante.getUsuario()));
                startActivity(i);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lista:
                Intent exito = new Intent(Inicio.this, Inicio.class);
                startActivity(exito);
                return true;
            case R.id.action_cursos:
                exito = new Intent(Inicio.this, Cursos.class);
                startActivity(exito);
                return true;
            case R.id.action_clave:
                exito = new Intent(Inicio.this, Clave.class);
                startActivity(exito);
                return true;
            case R.id.action_salir:
                exito = new Intent(Inicio.this, Login.class);
                startActivity(exito);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
