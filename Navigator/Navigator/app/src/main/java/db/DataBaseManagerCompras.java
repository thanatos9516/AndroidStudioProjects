package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.compra;
import model.detalle;

/**
 * Created by Johan on 20/04/2017.
 */

public class DataBaseManagerCompras extends DataBaseManager{

    private  static final  String Nombre_Tabla_Compra="compra";
    private  static final  String Nombre_Tabla_Detalle="detalle";


    private  static final  String CN_ID="_id";
    private  static final  String CN_ID_Compras="_id_Compras";
    private  static final  String CN_Descripcion="descripcion";
    private  static final  String CN_TOTAL="total";
    private  static final  String CN_PRECIO="precio";


    public   static final String CREATE_TABLE_COMPRA="create table "+ Nombre_Tabla_Compra + " ("
            + CN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CN_Descripcion + " text NULL, "
            + CN_TOTAL + " DECIMAL(10,5) NOT NULL DEFAULT 0.00000"
            +");";

    public   static final String CREATE_TABLE_DETALLE="create table "+ Nombre_Tabla_Detalle + " ("
            + CN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CN_ID_Compras + " INTEGER NULL, "
            + CN_Descripcion + " text NULL, "
            + CN_PRECIO + " DECIMAL(10,5) NULL, "
            +" FOREIGN KEY("+CN_ID_Compras+") REFERENCES "+Nombre_Tabla_Compra+"("+CN_ID+"));";

    public DataBaseManagerCompras(Context ctx) {

        super(ctx);
    }

    @Override
    public void cerrar()
    {
        super.getDb().close();
    }
    @Override
    public Cursor cargarCursorCompra() {
        String [] columnas =new String[]{CN_ID,CN_Descripcion,CN_TOTAL};

        return super.getDb().query(Nombre_Tabla_Compra,columnas,null,null,null,null,null);
    }

    @Override
    public Cursor cargarCursorDetalle(String id) {

     //   String [] columnas =new String[]{CN_ID,CN_ID_Compras,CN_Descripcion,CN_PRECIO};


        return  super.getDb().rawQuery("SELECT "+CN_ID+","+CN_ID_Compras+","+CN_Descripcion+","+CN_PRECIO+" FROM "+Nombre_Tabla_Detalle
                +" WHERE "+CN_ID_Compras+"= "+id,null);
    }

    private ContentValues generarContentValuesCompra(String id,String descripcion, String total)
    {
        ContentValues valores= new ContentValues();

        valores.put(CN_ID,id);
        valores.put(CN_Descripcion,descripcion);
        valores.put(CN_TOTAL,total);

        return valores;
    }


    @Override
    public void insert_compra(String id, String descripcion, String total) {

        Log.d("Compra_insertar", super.getDb().insert(Nombre_Tabla_Compra,null,generarContentValuesCompra(id,descripcion,total)) +" ");
    }

    @Override
    public void update_compra(String id, String descripcion, String total) {
        ContentValues valores= new ContentValues();

        valores.put(CN_Descripcion,descripcion);
        valores.put(CN_TOTAL,total);

        String [] args = new String[]{id};

        Log.d("compra_actualizar", super.getDb().update(Nombre_Tabla_Compra,valores,"_id=?",args)+" ");

    }

    @Override
    public void delete_compra(String id) {
        Log.d("compra_eliminar", super.getDb().delete(Nombre_Tabla_Compra,CN_ID+"=?",new String[]{id})+" ");
    }

    @Override
    public void update_total(String id, String total) {
        super.getDb().execSQL("UPDATE "+Nombre_Tabla_Compra+" SET "+CN_TOTAL +" = ("+CN_TOTAL+" + "+total+ ")  WHERE "+CN_ID+" = "+id);
    }

    @Override
    public void delete_all_compra() {
        super.getDb().execSQL(" ");
    }



    @Override
    boolean search_compra(String id) {
        Cursor resultSet = super.getDb().rawQuery("SELECT "+CN_ID+","+CN_Descripcion+","+CN_PRECIO+" FROM "+Nombre_Tabla_Compra
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
    public List<compra> getCompraList(){
        List<compra> list =new ArrayList<>();

        Cursor c = cargarCursorCompra();

        while (c.moveToNext()){

            compra Compra= new compra();

            Compra.setId(c.getString(0));
            Compra.setDescripcion(c.getString(1));
            Compra.setTotal(c.getDouble(2));

            list.add(Compra);
        }

        return list;
    }

    // DETALLES

    private ContentValues generarContentValuesDetalle(String id,String idcompra, String descripcion, String precio)
    {
        ContentValues valores= new ContentValues();

        valores.put(CN_ID,id);
        valores.put(CN_ID_Compras,idcompra);
        valores.put(CN_Descripcion,descripcion);
        valores.put(CN_PRECIO,precio);

        return valores;
    }
    @Override
    public void insert_detalle(String id,String idcompra, String descripcion, String precio) {
        Log.d("detalle_insertar", super.getDb().insert(Nombre_Tabla_Detalle,null,generarContentValuesDetalle(id,idcompra,descripcion,precio)) +" ");
    }

    @Override
    public void update_detalle(String id,String idcompra, String descripcion, String precio) {

    }

    @Override
    public void delete_detalle(String id) {

    }

    @Override
    public void delete_all_detalle() {

    }

    @Override
    boolean search_detalle(String id) {
        Cursor resultSet = super.getDb().rawQuery("SELECT "+CN_ID+","+CN_ID_Compras+","+CN_Descripcion+","+CN_PRECIO+" FROM "+Nombre_Tabla_Detalle
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
    public double getTotalCompra(String idCOMPRA){


        Cursor resultSet= super.getDb().rawQuery("SELECT sum("+CN_PRECIO+") from "+Nombre_Tabla_Detalle+" WHERE "+CN_ID_Compras+" = "+idCOMPRA+" ;", null);
        if(resultSet.moveToFirst())
            return   resultSet.getInt(0);
        else
            return   0.0;

    }
    public List<detalle> getDetalleList(String id){
        List<detalle> list =new ArrayList<>();

        Cursor c = cargarCursorDetalle(id);

        while (c.moveToNext()){

            detalle Detalle= new detalle();

            Detalle.setId(c.getString(0));
            Detalle.setIdCompra(c.getString(1));
            Detalle.setDescripcion(c.getString(2));
            Detalle.setPrecio(c.getDouble(3));

            list.add(Detalle);
        }

        return list;
    }

}
