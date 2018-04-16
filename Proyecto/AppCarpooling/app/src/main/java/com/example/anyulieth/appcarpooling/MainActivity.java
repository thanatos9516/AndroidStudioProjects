package com.example.anyulieth.appcarpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity{
//CONTROLADORES DEL LOGIN
   Button btnIngreso;
   Button btnRegistro;
   EditText txtUsu;
   EditText txtCla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INICIAR CONTROLADORES DEL LOGIN
        btnIngreso = (Button)findViewById(R.id.btnIngresar);

        btnRegistro = (Button)findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoFrom = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(nuevoFrom);
            }});
        txtUsu = (EditText)findViewById(R.id.txtCorreo);
        txtCla = (EditText)findViewById(R.id.txtClave);
    }

}
