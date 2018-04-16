package com.example.anyulieth.appcarpooling;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Anyulieth on 15/04/2018.
 */

public class HttpClientRegistro extends AsyncTask<Void, Void, String> {
    private ArrayList<Registro> listaRegistro;
    private RecyclerView recyclerRegistro;
    private RegistroAdapter mAdapter;

    private Context mContext;
    ProgressDialog progressDialog;

    private String cedula;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nacionalidad;
    private String correo;
    private String clave;

    public HttpClientRegistro(ArrayList<Registro> listaRegistro, RecyclerView recyclerRegistro,
                           RegistroAdapter mAdapter, Context mContext, String cedula, String nombre, String apellido1,
                              String apellido2,String nacionalidad,String correo, String clave) {
        this.listaRegistro = listaRegistro;
        this.recyclerRegistro = recyclerRegistro;
        this.mAdapter = mAdapter;
        this.mContext = mContext;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nacionalidad = nacionalidad;
        this.correo = correo;
        this.clave = clave;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "Cargando Registro de usuario", "Por favor espere");
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;
        try{            //falta verificar
            String wsUrl = "http://192.168.241.2:8080/AppCarpooling/RegistroUsuario.php?cedula=" + cedula +
                   "&nombre=" + nombre + "&apellido1=" + apellido1 + "&apellido2=" + apellido2 +
                    "&nacionalidad=" + nacionalidad + "&usu=" + correo + "&clave=" + clave;

            URL url = new URL(wsUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = inputStreamToString(in);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s){

        super.onPostExecute(s);
        progressDialog.dismiss();
        try{
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("timetable");

            listaRegistro = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){

                String cedula = jsonArray.getJSONObject(i).getString("cedula");
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String apellido1 = jsonArray.getJSONObject(i).getString("apellido1");
                String apellido2 = jsonArray.getJSONObject(i).getString("apellido2");
                String nacionalidad = jsonArray.getJSONObject(i).getString("nacionalidad");
                String correo = jsonArray.getJSONObject(i).getString("correo");
                String clave = jsonArray.getJSONObject(i).getString("clave");

                this.listaRegistro.add(new Registro(cedula, nombre,apellido1,apellido2,nacionalidad,correo,clave));
            }

            mAdapter = new RegistroAdapter();
            mAdapter.setmDataSet(listaRegistro);
            recyclerRegistro.setAdapter(mAdapter);
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String inputStreamToString(InputStream is){

        String rLine = "";
        StringBuilder response = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader rd = new BufferedReader(isr);
        try{
            while((rLine = rd.readLine()) != null){
                response.append(rLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response.toString();
    }
}
