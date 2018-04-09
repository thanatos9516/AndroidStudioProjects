package com.example.sarah.josechaves;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sarah on 14/04/2016.
 */
public class Cursos extends AppCompatActivity {
    private Button btnGuardar;
    DataBaseHelper helper = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
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
}
