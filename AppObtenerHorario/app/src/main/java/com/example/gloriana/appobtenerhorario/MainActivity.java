package com.example.gloriana.appobtenerhorario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Horario> horarios;
    private HorarioAdapter horarioAdapter;
    RecyclerView recycler_horario;
    private TextView lblHorarioActual;
    private String id;
    private String annio;
    private String periodo;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recycler_horario = (RecyclerView) findViewById(R.id.reciclador);
        recycler_horario.setHasFixedSize(true);
        lblHorarioActual = (TextView) findViewById(R.id.lblHorarioActual);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_horario.setLayoutManager(mLayoutManager);

        id = "115240001"; //504220374
        periodo="III";
        annio = "2014";//2016
        lblHorarioActual.setText("Horario " + periodo + "-" + annio);

        fillHorario();

    }

    private void fillHorario(){

        HttpClientHorario wsHorarios = new HttpClientHorario(horarios, recycler_horario,
                horarioAdapter, this, id, annio, periodo);
        wsHorarios.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
