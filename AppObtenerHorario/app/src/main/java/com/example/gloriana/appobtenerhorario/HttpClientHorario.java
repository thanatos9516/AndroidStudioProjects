package com.example.gloriana.appobtenerhorario;

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
 * Created by maste on 10/03/2018.
 */

public class HttpClientHorario extends AsyncTask<Void, Void, String> {

    private ArrayList<Horario> listaHorario;
    private RecyclerView recyclerHorario;
    private HorarioAdapter mAdapter;

    private Context mContext;
    ProgressDialog progressDialog;

    private String id;
    private String annio;
    private String periodo;

    public HttpClientHorario(ArrayList<Horario> listaHorario, RecyclerView recyclerHorario,
                             HorarioAdapter mAdapter, Context mContext, String id, String annio,
                             String periodo) {
        this.listaHorario = listaHorario;
        this.recyclerHorario = recyclerHorario;
        this.mAdapter = mAdapter;
        this.mContext = mContext;
        this.id = id;
        this.annio = annio;
        this.periodo = periodo;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "Cargando horario", "Por favor espere");
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;
        try{
            String wsUrl = "http://unruffled-crystal.000webhostapp.com/WSNotes/getTimeTable.php?user=" + id
                    + "&year=" + annio + "&period=" + periodo;

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

            listaHorario = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){

                String codigo = jsonArray.getJSONObject(i).getString("codigo");
                String curso = jsonArray.getJSONObject(i).getString("curso");
                String grupo = jsonArray.getJSONObject(i).getString("grupo");
                String horario = jsonArray.getJSONObject(i).getString("horario");
                String profesor = jsonArray.getJSONObject(i).getString("profesor");

                this.listaHorario.add(new Horario(codigo, curso, grupo, horario, profesor));
            }

            mAdapter = new HorarioAdapter();
            mAdapter.setmDataSet(listaHorario);
            recyclerHorario.setAdapter(mAdapter);
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
