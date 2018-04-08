package com.example.thanatos.examen1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;
/**
 * Created by Thanatos on 8/4/2018.
 */

public class Clave extends AppCompatActivity {

    private Button btnCambiar;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        btnCambiar= (Button) findViewById(R.id.btnGuardar);
        btnCambiar.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              String txtUsuario = ((EditText) findViewById(R.id.txtUsuarioClave)).getText().toString();
                                              String txtClaveVieja = ((EditText) findViewById(R.id.txtClaveVieja)).getText().toString();
                                              String txtClave = ((EditText) findViewById(R.id.txtClaveNueva)).getText().toString();
                                              String txtClave_repita = ((EditText) findViewById(R.id.txtRepita)).getText().toString();


                                              if(txtClave.equals(txtClave_repita)) {
                                                  Boolean comprobar = helper.cambiarclave(txtUsuario, txtClaveVieja, txtClave);
                                                  if (comprobar)
                                                  {
                                                      Intent exito = new Intent(Clave.this, Login.class);
                                                      startActivity(exito);
                                                  }
                                                  else
                                                  {
                                                      Toast error = Toast.makeText(Clave.this, "Contraseña incorrecta!", Toast.LENGTH_SHORT);
                                                      error.show();
                                                  }

                                              }
                                          }
                                      }
        );}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ver_cursos:
                Intent exito = new Intent(Clave.this,Cursos.class);
                startActivity(exito);
                return true;
            case R.id.Estudiantes:
                exito = new Intent(Clave.this, List.class);
                startActivity(exito);
                return true;
            case R.id.salir:
                exito = new Intent(Clave.this, Login.class);
                startActivity(exito);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
