package com.example.thanatos.examen1;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
                            textView.setText(progressStatus + "/" + progressBar.getMax());
                        }
                    });
                    try {
                        Thread.sleep(5);


                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }
//Si es primera vez descomentar
                DataBaseHelper db = new DataBaseHelper(getApplicationContext());
                db.ingresarEstudiante(new Estudiante(123,"José Molina","123"));
                db.ingresarEstudiante(new Estudiante(124,"Ginnette Cascante","123"));
                db.ingresarEstudiante(new Estudiante(125,"Gladys Cardoza Cruz","123"));

                db.ingresarCurso("Moviles");
                db.ingresarCurso("Web");
                db.ingresarCurso("Python");

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
