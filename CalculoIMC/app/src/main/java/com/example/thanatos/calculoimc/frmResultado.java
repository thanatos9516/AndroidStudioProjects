package com.example.thanatos.calculoimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class frmResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_resultado);
        TextView lblResultado = (TextView) findViewById(R.id.lblResultado);
        String strimc = getIntent().getExtras().getString("imc");
        double imc=Double.parseDouble(strimc);

        String msg="";
        if (imc<16){
            msg="Delgadez severa";

        }else  if (imc<=16.99){
            msg="Delgadez moderada";
        }else if (imc<18.49){
            msg="Delgadez aceptable";

        }else if (imc<=24.99){
            msg="Peso normal";
        }else {
            msg="Sobre peso";
        }
        lblResultado.setText("Indice de masa corporal :  "+imc+"\n"+msg);
    }
}