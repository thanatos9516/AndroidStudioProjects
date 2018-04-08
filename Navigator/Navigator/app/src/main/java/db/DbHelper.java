package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Johan on 19/03/2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private  static  final  String DB_NOMBRE="compras.sqlite";
    private  static  int DB_SCHEME_VERSION=1;


    public DbHelper(Context context) {

        super(context, DB_NOMBRE,null,DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //crear una tabla cursor
        db.execSQL(DataBaseManagerCompras.CREATE_TABLE_COMPRA);
        db.execSQL(DataBaseManagerCompras.CREATE_TABLE_DETALLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL("DROP TABLE IF EXISTS"+DB_NOMBRE);
        onCreate(db);
    }
}
