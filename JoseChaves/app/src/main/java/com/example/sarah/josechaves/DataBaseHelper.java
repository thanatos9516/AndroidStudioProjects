package com.example.sarah.josechaves;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sarah on 13/04/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    public static final int BD_VERSION = 1;
    public static final String BD_NOMBRE = "ExamenGl.db";
    public static final String TABLA_NOMBRE = "tblestudiante";
    public static final String TABLA_CURSO = "tblcursos";
    public static final String TABLA_CALIFICACIONES= "tblcalificaciones";
    public static final String COLUMNA_USUARIO = "usuario";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_CLAVE = "clave";
    public static final String COLUMNA_ID_CURSO = "IdCurso";
    public static final String COLUMNA_NOMBRE_CURSO = "NombreCurso";
    public static final String COLUMNA_CALIFICACIONES = "calificacion";

    SQLiteDatabase db;

    public static final String CREAR_TABLA = "create table tblestudiante(usuario text not null,"+
            "nombre text not null, clave text not null);";
    public static final String CREAR_TABLA_CURSO = "create table " + TABLA_CURSO + "(IdCurso integer primary key autoincrement, "+
            "NombreCurso text not null);";
    public static final String CREAR_TABLA_CALIFICACIONES = "create table " + TABLA_CALIFICACIONES + "(IdCurso integer not null, "+
            "IdUsuario text not null, calificacion real not null);";

    public DataBaseHelper(Context context)
    {
        super(context, BD_NOMBRE,null, BD_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);
        this.db = db;
        db.execSQL(CREAR_TABLA_CURSO);
        this.db = db;
        db.execSQL(CREAR_TABLA_CALIFICACIONES);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLA_NOMBRE;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS" + TABLA_CURSO;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS" + TABLA_CALIFICACIONES;
        this.onCreate(db);
    }


    public boolean ingresarEstudiante(Estudiante estudiante)
    {
        db = this.getWritableDatabase();
        String query = "Select *from " + TABLA_NOMBRE + " where " + COLUMNA_USUARIO + " = "+estudiante.getUsuario();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() == 0)
        {
            ContentValues valores = new ContentValues();
            valores.put(COLUMNA_USUARIO, estudiante.getUsuario());
            valores.put(COLUMNA_CLAVE, estudiante.getClave());
            valores.put(COLUMNA_NOMBRE, estudiante.getNombre());

            db.insert(TABLA_NOMBRE, null, valores);
            db.close();
            return true;
        }
        else {
            return false;
        }


    }

    public String buscarEstudiante(String usuario){
        db = this.getWritableDatabase();
        String query = "select *from " + TABLA_NOMBRE;
        Cursor cursor = db.rawQuery(query, null);
        String user;
        String clave = "No encontrado";
        if(cursor.moveToFirst()){
            do{
                user = cursor.getString(0);
                if(user.equals(usuario)){
                    clave = cursor.getString(2);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return clave;
    }
    public Boolean ingresarCurso(String nombre){
        try
        {
            String query = "select *from " + TABLA_CURSO;
            db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            int cantidad1 = cursor.getCount();
            ContentValues valores = new ContentValues();
            valores.put(COLUMNA_NOMBRE_CURSO, nombre);

            db.insert(TABLA_CURSO, null, valores);
            cursor = db.rawQuery(query, null);
            int cantidad2 = cursor.getCount();
            db.close();
            if(cantidad2>cantidad1)
                return true;
            else return false;
        }
        catch (Exception e)
        {
            return false;
        }

    }
    public Boolean ingresarNota(int idCurso, int idUsuario, double calificacion){
        try
        {
            db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(COLUMNA_ID_CURSO, idCurso);
            valores.put("IdUsuario", idUsuario);
            valores.put(COLUMNA_CALIFICACIONES, calificacion);

            db.insert(TABLA_CALIFICACIONES, null, valores);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public Boolean modificarClave(String usuario, String claveVieja, String claveNueva){
        db = this.getWritableDatabase();
        String query = "select *from " + TABLA_NOMBRE;
        String whereClause = COLUMNA_USUARIO+ " = " + usuario ;
        String user;
        String clave= "no encontrado";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){

            do {
                user = cursor.getString(0);
                if(user.equals(usuario)){
                    clave = cursor.getString(2);
                    if(clave.equals(claveVieja)){
                    ContentValues valores = new ContentValues();
                    valores.put(COLUMNA_USUARIO, usuario);
                    valores.put(COLUMNA_CLAVE, claveNueva);
                    db.update(TABLA_NOMBRE, valores, COLUMNA_USUARIO + "= ?", new String[] {usuario});

                    }
                }

            }
            while(cursor.moveToNext());
            return true;
        }
        else
            return false;
    }

    public List<Estudiante> listaEstudiante(){
        db = this.getWritableDatabase();
        List <Estudiante> estudiantes = new LinkedList<Estudiante>();
        String query = "Select *from " + TABLA_NOMBRE;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Estudiante estudiante = new Estudiante(cursor.getInt(0),cursor.getString(1),"");
                estudiantes.add(estudiante);
            }
            while (cursor.moveToNext());

        }


        return estudiantes;
    }
    public List<Calificacion> listaNotas(int usuario){
        db = this.getWritableDatabase();
        String query = "Select *from " + TABLA_CALIFICACIONES;
        List <Calificacion> calificaciones = new LinkedList<Calificacion>();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                if(cursor.getInt(1) == usuario)
                {
                    Calificacion calificacion = new Calificacion();
                    calificacion.setIdCurso(cursor.getInt(0));
                    calificacion.setIdUsuario(cursor.getInt(1));
                    calificacion.setCalificacion(cursor.getDouble(2));
                    calificaciones.add(calificacion);
                }

            }
            while (cursor.moveToNext());

        }

        return calificaciones;
    }


}
