package com.example.thanatos.examen1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.*;

/**
 * Created by Thanatos on 8/4/2018.
 */

public class Notas extends AppCompatActivity {
    ListView lista;
    // SQLite_OpenHelper helper = new SQLite_OpenHelper(getApplicationContext());
    java.util.List<String> item=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        lista= (ListView)findViewById(R.id.listNotas);
        mostrarNotas();
    }

    private void mostrarNotas(){
        SQLite_OpenHelper helper= new SQLite_OpenHelper(this);
        Cursor c= helper.getCursor();
        item = new ArrayList<String>();
        int idCurso;
        int idEstudiante;
        double nota;

        if(c.moveToFirst()){
            do{
                idCurso= c.getInt(0);
                idEstudiante= c.getInt(1);
                nota= c.getDouble(2);
                item.add(idCurso+""+idEstudiante+""+nota);

            }while(c.moveToNext());

        }
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item);
        lista.setAdapter(adaptador);



    }
}
