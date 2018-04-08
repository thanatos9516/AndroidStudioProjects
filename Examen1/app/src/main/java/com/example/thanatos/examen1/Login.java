package com.example.thanatos.examen1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Thanatos on 8/4/2018.
 */

public class Login extends Activity {

    SQLite_OpenHelper helper= new SQLite_OpenHelper(this);


    private EditText txtUsuario;
    private EditText txtContra;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        btnlogin= (Button)findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsuario= ((EditText )findViewById(R.id.textUsuario)).getText().toString();
                String txtContra= ((EditText )findViewById(R.id.passContra)).getText().toString();

                String usuarioValor= txtUsuario;
                String claveValor=txtContra;



                if (txtUsuario.equals("") || txtUsuario.equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios en blanco", Toast.LENGTH_SHORT).show();
                } else {
                    String comprobante = helper.buscarUsuario(usuarioValor);
                    if (claveValor.equals(comprobante)) {
                        Intent exito = new Intent(Login.this, List.class);
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
