package com.example.sarah.josechaves;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sarah on 13/04/2016.
 */
public class Login extends AppCompatActivity {
    DataBaseHelper helper = new DataBaseHelper(this);

    private Button btnRegistrar;
    private Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegistrar= (Button)findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoFrom = new Intent(Login.this, SignUp.class);
                startActivity(nuevoFrom);
            }});
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                String txtUsuario = ((EditText) findViewById(R.id.txtUsuarioLogin)).getText().toString();
                String txtClave = ((EditText) findViewById(R.id.txtClaveLogin)).getText().toString();
                String usuarioValor = txtUsuario;
                String claveValor = txtClave;


                if (txtUsuario.equals("") || txtClave.equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios en blanco", Toast.LENGTH_SHORT).show();
                } else {
                    String comprobante = helper.buscarEstudiante(usuarioValor);
                    if (claveValor.equals(comprobante)) {
                        Intent exito = new Intent(Login.this, Inicio.class);
                        exito.putExtra("Usuario", usuarioValor);
                        startActivity(exito);
                    } else {
                        Toast error = Toast.makeText(Login.this, "Usuario no registrado", Toast.LENGTH_SHORT);
                        error.show();
                    }
                }
            }});
    }
}
