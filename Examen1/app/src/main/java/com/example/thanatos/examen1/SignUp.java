package com.example.thanatos.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private Button btnRegistrarse;
    DataBaseHelper helper = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegistrarse= (Button)findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usuario = (EditText) findViewById(R.id.txtUsuario);
                EditText nombre = (EditText) findViewById(R.id.txtNombre);
                EditText clave = (EditText) findViewById(R.id.txtClave);

                String usuarioValor = usuario.getText().toString();
                String nombreValor = nombre.getText().toString();
                String claveValor = clave.getText().toString();

                if (usuarioValor == "" || nombreValor == "" || claveValor == ""){
                    Toast error = Toast.makeText(SignUp.this, "Rellene campos!", Toast.LENGTH_SHORT);
                    error.show();
                }
                else{
                    Estudiante estudiante = new Estudiante(Integer.parseInt(usuarioValor),nombreValor,claveValor);
                    boolean registrar = helper.ingresarEstudiante(estudiante);
                    if(registrar)
                    {
                        Intent exito = new Intent(SignUp.this, Inicio.class);
                        exito.putExtra("Usuario", usuarioValor);
                        startActivity(exito);
                    }
                    else
                    {
                        Toast error = Toast.makeText(SignUp.this, "Usuario existente, intente con otra identificación!", Toast.LENGTH_SHORT);
                        error.show();
                    }

                }
            }
        });



    }
}
