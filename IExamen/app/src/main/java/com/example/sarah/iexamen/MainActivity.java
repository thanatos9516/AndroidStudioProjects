package com.example.sarah.iexamen;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    private ProgressBar progressBar;
    private int progressStatus=0;
    private TextView textView;
    private Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus + "%" );
                        }
                    });
                    try {
                        Thread.sleep(50);


                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }


                SQLite_OpenHelper db= new SQLite_OpenHelper(getApplicationContext());

                db.ingresarUsuario(new Usuario(123,"123"));

                db.ingresarEstudiante(604240231, "- Jose Molina Cascante");
                db.ingresarEstudiante(503830818, "- Gladys Cardoza Cruz");
                db.ingresarEstudiante(503830333, "- Ginnette Cascante Prado");

                db.agregarCurso("Moviles");
                db.agregarCurso("Interfaces");
                db.agregarCurso("Ingenieria de Sistemas");

                db.ingresarNota(1, 123, 89);
                db.ingresarNota(2,123,89);
                db.ingresarNota(2,123,89);

                db.ingresarNota(1, 124, 89);
                db.ingresarNota(2,124,89);
                db.ingresarNota(2,124,89);

                db.ingresarNota(1, 125, 89);
                db.ingresarNota(2,125,89);
                db.ingresarNota(2,125,89);

                Intent nuevoFrom= new Intent(MainActivity.this, Login.class);
                startActivity(nuevoFrom);

                    finish();



            }
        }).start();

    }

}
