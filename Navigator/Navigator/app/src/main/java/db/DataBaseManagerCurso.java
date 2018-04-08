package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.curso;

/**
 * Created by Johan on 19/03/2017.
 */
/*
public class DataBaseManagerCurso extends  DataBaseManager {

    private  static final  String Nombre_Tabla="curso";

    private  static final  String CN_ID="_id";
    private  static final  String CN_Nombre="nombre";
    private  static final  String CN_Descripcion="descripcion";
    private  static final  String CN_PRECIO="precio";

    public   static final String CREATE_TABLE="create table "+ Nombre_Tabla + " ("
            + CN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CN_Nombre + " text NOT NULL, "
            + CN_Descripcion + " text NULL, "
            + CN_PRECIO + " DECIMAL(10,5) NULL "
            +");";

    public DataBaseManagerCurso(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar()
    {
        super.getDb().close();
    }

    private ContentValues generarContentValues(String id, String nombre, String descripcion, String precio)
    {
        ContentValues valores= new ContentValues();

        valores.put(CN_ID,id);
        valores.put(CN_Nombre,nombre);
        valores.put(CN_Descripcion,descripcion);
        valores.put(CN_PRECIO,precio);

        return valores;
    }



    @Override
    public void insert_Curso(String id, String nombre, String descripcion, String precio) {

        Log.d("curso_insertar", super.getDb().insert(Nombre_Tabla,null,generarContentValues(id,nombre,descripcion,precio)) +" ");
    }

    @Override
    public void update_Curso(String id, String nombre, String descripcion, String precio) {


        ContentValues valores= new ContentValues();

        valores.put(CN_Nombre,nombre);
        valores.put(CN_Descripcion,descripcion);
        valores.put(CN_PRECIO,precio);

        String [] args = new String[]{id};

        Log.d("curso_actualizar", super.getDb().update(Nombre_Tabla,valores,"_id=?",args)+" ");
    }

    @Override
    public void delete_curso(String id) {
        Log.d("curso_eliminar", super.getDb().delete(Nombre_Tabla,CN_ID+"=?",new String[]{id})+" ");
    }

    @Override
    public void delete_all_curso() {
         super.getDb().execSQL("DELETE FROM "+Nombre_Tabla+" ;");
         Log.d("curso_eliminar","datos borados");
    }

    @Override
    public Cursor cargarCursor() {
        String [] columnas =new String[]{CN_ID,CN_Nombre,CN_Descripcion,CN_PRECIO};

        return super.getDb().query(Nombre_Tabla,columnas,null,null,null,null,null);
    }

    @Override
    boolean search_curso(String id) {

        Cursor resultSet = super.getDb().rawQuery("SELECT "+CN_ID+","+CN_Nombre+","+CN_Descripcion+","+CN_PRECIO+" FROM "+Nombre_Tabla
                                                  +" WHERE "+CN_ID+"= "+id,null);

        if(resultSet.getCount()>=0)
        {
            return  true;
        }
        else
        {
            return false;
        }

    }
    public List<curso> getCursosList(){
        List<curso> list =new ArrayList<>();

        Cursor c = cargarCursor();

        while (c.moveToNext()){

            curso Curso= new curso();

            Curso.setId(c.getString(0));
            Curso.setNombre(c.getString(1));
            Curso.setDescripcion(c.getString(2));
            Curso.setPrecio(c.getDouble(3));

            list.add(Curso);
        }

        return list;
    }
}*/