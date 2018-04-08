package com.example.sarah.iexamen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sarah on 12/04/2016.
 */
public class SQLite_OpenHelper extends SQLiteOpenHelper{

    public static final int VERSION_BD=1;
    public static final String NOMBRE_BD= "Exa.db";
    public static final String TABLA_USUARIO= "tab_usuario";
    public static final String TABLA_ESTUDIANTE= "tab_estudiante";
    public static final String TABLA_CURSOS="tab_cursos";
    public static final String TABLA_NOTAS= "tab_notas";
    public static final String COLUMNA_USUARIO="id_usuario";
    public static final String COLUMNA_PASSWORD="pass";
    public static final String COLUMNA_ID_EST="IdEstudiante";
    public static final String COLUMNA_NOMBRE="nombre_estudiante";
    public static final String COLUMNA_IDCURSO="IdCurso";
    public static final String COLUMNA_CURSO_NOM="NombreCurso";
    public static final String COLUMNA_NOTAS="nota";

    SQLiteDatabase db;

    public static final String CREAR_TABLA_USUARIO= "create table tab_usuario(id_usuario text not null,"+
                           "pass text not null);";

   public static final String CREAR_TABLA_ESTUDIANTE="create table tab_estudiante(IdEstudiante text not null,"+
                                  "nombre_estudiante text not null);";

    public static final String CREAR_TABLA_CURSO="create table " +  TABLA_CURSOS +"(IdCurso integer primary key autoincrement,"+
                                   "NombreCurso text not null);";
//    public static final String CREAR_TABLA_NOTAS="create table"+TABLA_NOTAS+"(IdCurso integer not null,"+
//                                    "IdEstudiante text not null, nota real not null);";



     public SQLite_OpenHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD); // en name entra el nombre de nuestra bd
    }

        @Override
    public void onCreate(SQLiteDatabase db) {// crear estructura de nuestras tablas
            db.execSQL(CREAR_TABLA_USUARIO);
            this.db=db;
           db.execSQL(CREAR_TABLA_ESTUDIANTE);
           this.db=db;
           db.execSQL(CREAR_TABLA_CURSO);
            this.db=db;
//            db.execSQL(CREAR_TABLA_NOTAS);
  //          this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // si se quiere modificar algun tipo de estructura
       String query= "DROP TABLE IF EXISTS"+TABLA_USUARIO;
        db.execSQL(query);
       query="DROP TABLE IF EXISTS"+TABLA_ESTUDIANTE;
        db.execSQL(query);
        query="DROP TABLE IF EXISTS"+TABLA_CURSOS;
        db.execSQL(query);
//        query="DROP TABLE IF EXISTS"+TABLA_NOTAS;
//        this.onCreate(db);
    }

    public boolean ingresarUsuario(Usuario usuario){
        db= this.getWritableDatabase();// se puede escribir en la bd
        String query="Select *from "+ TABLA_USUARIO + " where " + COLUMNA_USUARIO + " = "+usuario.getUsuario();
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.getCount() == 0)
        {
            ContentValues valores= new ContentValues();
            valores.put(COLUMNA_USUARIO, usuario.getUsuario());
            valores.put(COLUMNA_PASSWORD,usuario.getPass());

            db.insert(TABLA_USUARIO,null,valores);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }


//    public boolean ingresarEstudiante(Estudiante estudiante){
//
//        db= this.getWritableDatabase();// se puede escribir en la bd
//        String query="Select *from "+ TABLA_ESTUDIANTE;
//        Cursor cursor= db.rawQuery(query,null);
//        if(cursor.getCount() == 0)
//        {
//            ContentValues valores= new ContentValues();
//            valores.put(COLUMNA_ID_EST, estudiante.getIdEstudiante());
//            valores.put(COLUMNA_NOMBRE,estudiante.getNombre());
//
//
//            db.insert(TABLA_ESTUDIANTE,null,valores);
//            db.close();
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }


    public String buscarUsuario(String usuario){
        db=this.getWritableDatabase();
        String query= "select *from " + TABLA_USUARIO;
        Cursor cursor= db.rawQuery(query,null);
        String user;
        String pass="No registrado";
        if(cursor.moveToFirst()){
            do{
                user= cursor.getString(0);
                if(user.equals(usuario)){
                    pass= cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return pass;

    }
//
//    public String buscarEstudiante(String estudiante){
//        db=this.getWritableDatabase();
//        String query= "select *from " + TABLA_ESTUDIANTE;
//        Cursor cursor= db.rawQuery(query,null);
//        String est;
//        String id="No registrado";
//        if(cursor.moveToFirst()){
//            do{
//                est= cursor.getString(0);
//                if(id.equals(estudiante)){
//                    id= cursor.getString(0);
//                    break;
//                }
//            }
//            while (cursor.moveToNext());
//        }
//        return id;
//
//    }

//public Boolean ingresarCurso(String nombre){
//    try {
//        String query = "select *from " + TABLA_CURSOS;
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        int cantidad = cursor.getCount();
//        ContentValues valores = new ContentValues();
//        valores.put(COLUMNA_CURSO_NOM, nombre);
//
//        db.insert(TABLA_CURSOS, null, valores);
//        cursor = db.rawQuery(query, null);
//        int cantidad1 = cursor.getCount();
//        db.close();
//        if (cantidad1 > cantidad)
//            return true;
//        else return false;
//    }
//        catch (Exception e)
//       {
//            return false;
//        }
//}
    public void agregarCurso(String nombre){
    ContentValues valores= new ContentValues();
    valores.put(COLUMNA_CURSO_NOM,nombre);
        this.getWritableDatabase().insert(TABLA_CURSOS,null,valores);

    }

    public Cursor getCursor(){
    String columnas[]={COLUMNA_IDCURSO,COLUMNA_CURSO_NOM};
        Cursor c= this.getReadableDatabase().query(TABLA_CURSOS,columnas,null,null,null,null,null);
     return c;
}

    public Boolean cambiarclave(String usuario, String claveantigua, String clavenueva){
        db = this.getWritableDatabase();
        String query = "select *from " + TABLA_USUARIO;
        String whereClause = COLUMNA_USUARIO+ " = " + usuario ;
        String user;
        String clave= "no encontrado";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){

            do {
                user = cursor.getString(0);
                if(user.equals(usuario)){
                    clave = cursor.getString(2);
                    if(clave.equals(claveantigua)){
                        ContentValues valores = new ContentValues();
                        valores.put(COLUMNA_USUARIO, usuario);
                        valores.put( COLUMNA_PASSWORD, clavenueva);
                        db.update(TABLA_USUARIO, valores, COLUMNA_USUARIO + "= ?", new String[] {usuario});

                    }
                }

            }
            while(cursor.moveToNext());
            return true;
        }
        else
            return false;
    }


    public void ingresarEstudiante(int id, String nombre ){
        ContentValues valores= new ContentValues();
        valores.put(COLUMNA_ID_EST,id);
        valores.put(COLUMNA_NOMBRE,nombre);
        this.getWritableDatabase().insert(TABLA_ESTUDIANTE,null,valores);

    }

    public Cursor getEstudiante(){
        String columnas[]={COLUMNA_ID_EST,COLUMNA_NOMBRE};
        Cursor c= this.getReadableDatabase().query(TABLA_ESTUDIANTE, columnas, null, null, null, null, null);
        return c;
    }

    public void ingresarNota(int idCurso, int IdEstudiante, double nota ){
        ContentValues valores= new ContentValues();
        valores.put(COLUMNA_IDCURSO,idCurso);
        valores.put(COLUMNA_ID_EST,IdEstudiante);
        valores.put(COLUMNA_NOTAS,nota);
        this.getWritableDatabase().insert(TABLA_NOTAS,null,valores);

    }

    public Cursor getNota(int idEstudiante){
        String columnas[]={COLUMNA_IDCURSO,COLUMNA_ID_EST,COLUMNA_NOTAS};
        Cursor c= this.getReadableDatabase().query(TABLA_NOTAS, columnas, null, null, null, null, null);
        return c;
    }







}


