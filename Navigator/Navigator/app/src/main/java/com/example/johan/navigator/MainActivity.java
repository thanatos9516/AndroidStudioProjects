package com.example.johan.navigator;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adapters.cartaCursoAdacter;
import db.DataBaseManager;
import db.DataBaseManagerCompras;
import layout.formulario1;
import layout.formulario2;
import layout.formulario3;
import model.compra;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button bntInsert,btnUpdate,btnDelete,btnGetAll;
    private RecyclerView recycler;
    private cartaCursoAdacter adapter;
    private RecyclerView.LayoutManager lManager;
    private List<compra> listarItemsCompra;
    private DataBaseManagerCompras managercompra;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        adViews();
        managercompra = new DataBaseManagerCompras(this);
        listarItemsCompra = managercompra.getCompraList();
        inicializarRecicler();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = "MenuLateralDeslizante";
        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new formulario1();
            title = "CAMERA";
        } else if (id == R.id.nav_gallery) {
            fragment = new formulario2();
            title = "GALLERY";
        } else if (id == R.id.nav_slideshow) {
            fragment = new formulario3();
            title = "SLIDESHOW";
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        }

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle(title);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    private void adViews(){
        bntInsert =(Button)findViewById(R.id.btnInsertar);

        bntInsert.setOnClickListener(this);
       /* btnUpdate =(Button)findViewById(R.id.btnActualizar);
        btnUpdate.setOnClickListener(this);
        btnDelete=(Button)findViewById(R.id.btnEliminar);
        btnDelete.setOnClickListener(this);*/
        btnGetAll=(Button)findViewById(R.id.btnConsulta);
        btnGetAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.btnInsertar:

                  AlertDialog.Builder mBuilder= new AlertDialog.Builder(MainActivity.this);
                  View mView = getLayoutInflater().inflate(R.layout.dialog_agreagar,null);

                  final EditText mDescrip=(EditText)mView.findViewById(R.id.editTextNombre);

                  Button mAgregar=(Button)mView.findViewById(R.id.btngurdar);
                  mBuilder.setView(mView);
                  final AlertDialog dialog = mBuilder.create();
                  dialog.show();
                  mAgregar.setOnClickListener(new View.OnClickListener(){
                      @Override
                      public void onClick(View v) {
                        if( !mDescrip.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"Guardado éxitoso",Toast.LENGTH_SHORT).show();
                            managercompra.insert_compra(null,mDescrip.getText().toString(),"0");
                            recargarRecycler();
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Completar el campo Nombre",Toast.LENGTH_SHORT).show();
                        }

                      }
                  });

                  break;
            //  case R.id.btnActualizar:
                 /* for (int i=0;i<listarItemsCurso.size();i++){
                      managercurso.insert_Curso(listarItemsCurso.get(i).getId(),"Master curso"+i,"Descripcion personalisada"+i,""+i*5);
                  }*/
             //     break;
             // case R.id.btnEliminar:
               //  managercurso.delete_all_curso();
             //     break;
              case R.id.btnConsulta:
                  recargarRecycler();
                  break;
          }
    }
    private void  recargarRecycler(){
        listarItemsCompra= managercompra.getCompraList();
        adapter = new cartaCursoAdacter(this,listarItemsCompra);
        recycler.setAdapter(adapter);
    }

    public void inicializarRecicler(){

        recycler=(RecyclerView)findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter= new cartaCursoAdacter(this,listarItemsCompra);

        recycler.setAdapter(adapter);

        recycler.setItemAnimator(new DefaultItemAnimator());
    }

  /*  public void listarCompra(View vista)
    {
        recycler=(RecyclerView)findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter= new cartaCursoAdacter(this,listarItemsCompra);

        recycler.setAdapter(adapter);

        recycler.setItemAnimator(new DefaultItemAnimator());
    }*/
}
