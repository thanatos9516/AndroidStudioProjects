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

public class HttpClientLogin extends AsyncTask<Void, Void, String> {
    private ArrayList<Login> listaLogin;
    private RecyclerView recyclerLogin;
    private LoginAdapter mAdapter;

    private Context mContext;
    ProgressDialog progressDialog;

    private String correo;
    private String clave;

    public HttpClientLogin(ArrayList<Login> listaLogin, RecyclerView recyclerLogin,
                             LoginAdapter mAdapter, Context mContext, String correo, String clave) {
        this.listaLogin = listaLogin;
        this.recyclerLogin = recyclerLogin;
        this.mAdapter = mAdapter;
        this.mContext = mContext;
        this.correo = correo;
        this.clave = clave;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "Cargando Logueo", "Por favor espere");
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;
        try{
            String wsUrl = "http://192.168.241.2:8080/AppCarpooling/ValidarLogin.php?usu=" + correo + "&clave=" + clave;

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

            listaLogin = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){

                String correo = jsonArray.getJSONObject(i).getString("correo");
                String clave = jsonArray.getJSONObject(i).getString("clave");

                this.listaLogin.add(new Login(correo, clave));
            }

            mAdapter = new LoginAdapter();
            mAdapter.setmDataSet(listaLogin);
            recyclerLogin.setAdapter(mAdapter);
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
