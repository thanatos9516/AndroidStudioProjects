package db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Johan on 19/03/2017.
 */
public abstract class DataBaseManager {

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context ctx) {
        helper= new DbHelper(ctx);
        db= helper.getWritableDatabase();
    }
    public DbHelper getHelper() {
        return helper;
    }

    public void setHelper(DbHelper helper) {
        this.helper = helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public void cerrar()
    {
        db.close();
    }
    abstract public Cursor cargarCursorCompra();
    abstract public Cursor cargarCursorDetalle(String id);

    abstract public void insert_compra(String id,String descripcion,String total);
    abstract public void update_compra(String id,String descripcion,String total);
    abstract public void delete_compra(String id);
    abstract public void update_total(String id,String total);
    abstract public void delete_all_compra();
    abstract boolean search_compra(String id);

    abstract public void insert_detalle(String id,String idcompra,String descripcion,String precio);
    abstract public void update_detalle(String id,String idcompra,String descripcion,String precio);
    abstract public void delete_detalle(String id);
    abstract public void delete_all_detalle();
    abstract boolean search_detalle(String id);
}
