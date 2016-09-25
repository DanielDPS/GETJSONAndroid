package com.example.dany.gamaslt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetalleOrden extends AppCompatActivity {

    ListView listaOrdenes,listaDetalle,listaEstatusDetalle;
    android.support.v7.app.ActionBar actionBar;
    protected ArrayList<Orden> ConvertOrdenToList(Orden orden){
        ArrayList<Orden> lst = new ArrayList<>();
        lst.add(orden);
        return lst;
    }
    protected ArrayList<Detalle> ConvertDetalleToDetalleList(Detalle detalle){
        ArrayList<Detalle> lst = new ArrayList<>();
        lst.add(detalle);
        return lst;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_orden);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
       // overridePendingTransition(R.animator.zoom_back_out,R.animator.zoom_back_out);


        listaOrdenes = (ListView) findViewById(R.id.listaOrden);
        listaDetalle = (ListView)findViewById(R.id.listaDetalle);
        listaEstatusDetalle = (ListView)findViewById(R.id.listaEstatusDetalle);
        Intent intent = getIntent();
        Orden or = (Orden) intent.getSerializableExtra("Orden");

        AdaptadorOrdenDetalle adaptador = new AdaptadorOrdenDetalle(DetalleOrden.this, ConvertOrdenToList(or));
        AdaptadorDetalle adaptadorDetalle = new AdaptadorDetalle(DetalleOrden.this, ConvertDetalleToDetalleList(or.getDetalle()));
        AdaptadorEstatus adaptadorEstatus = new AdaptadorEstatus(DetalleOrden.this, or.getDetalle().getEstatusArrayList());

        listaOrdenes.setAdapter(adaptador);
        listaDetalle.setAdapter(adaptadorDetalle);
        listaEstatusDetalle.setAdapter(adaptadorEstatus);

        actionBar.setTitle("Detalle Orden");
        String titulo = "#" + String.valueOf(or.getNumero());
        actionBar.setSubtitle(titulo);



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
