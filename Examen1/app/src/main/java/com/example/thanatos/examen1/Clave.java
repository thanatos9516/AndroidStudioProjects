package com.example.thanatos.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Clave extends AppCompatActivity {
    private Button btnGuardar;
    DataBaseHelper helper = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                String txtUsuario = ((EditText) findViewById(R.id.txtUsuarioClave)).getText().toString();
                String txtClaveVieja = ((EditText) findViewById(R.id.txtClaveVieja)).getText().toString();
                String txtClave = ((EditText) findViewById(R.id.txtClaveNueva)).getText().toString();
                String txtClave2 = ((EditText) findViewById(R.id.txtConfirmacion)).getText().toString();


                    if(txtClave.equals(txtClave2)) {
                        Boolean comprobar = helper.modificarClave(txtUsuario, txtClaveVieja, txtClave);
                        if (comprobar)
                        {
                            Intent exito = new Intent(Clave.this, Inicio.class);
                            startActivity(exito);
                        }
                        else
                        {
                            Toast error = Toast.makeText(Clave.this, "Contraseña incorrecta!", Toast.LENGTH_SHORT);
                            error.show();
                        }

                    }
                }});
    }
}
