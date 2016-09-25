package com.example.dany.gamaslt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dany on 03/04/2016.
 */
public class Adaptador extends ArrayAdapter<Orden> {


    public  static  class  Objetos{
        TextView Numero;
        //TextView Operacion;
        TextView TipoUnidad;
        TextView Unidad;
        TextView Fecha;
        TextView Hora;
        ImageView Imagen;
    }
    private Context context;
    public  Adaptador(Context context, ArrayList<Orden> ordenes){
        super(context,R.layout.orden,ordenes);
        this.context = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Orden p = getItem(position);
        Objetos datos;

        if (convertView == null){

            datos = new Objetos();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.orden,parent,false);
            datos.Imagen  = (ImageView)convertView.findViewById(R.id.imagenOrden);
           // datos.Operacion = (TextView)convertView.findViewById(R.id.txtOperacion);
            datos.TipoUnidad= (TextView)convertView.findViewById(R.id.txtTipoUnidad);
            datos.Unidad = (TextView)convertView.findViewById(R.id.txtUnidad);
            datos.Fecha = (TextView)convertView.findViewById(R.id.txtFecha);
            datos.Hora = (TextView)convertView.findViewById(R.id.txtHora);
            datos.Numero= (TextView)convertView.findViewById(R.id.txtNumero);
            convertView.setTag(datos);

        }
        else {

            datos = (Objetos)convertView.getTag();
        }
        datos.Numero.setText(Integer.toString(p.getNumero()));
       // datos.Operacion.setText(p.getOperacion());
        datos.TipoUnidad.setText(p.getTipoUnidad());
        datos.Unidad.setText(p.getUnidad());
        datos.Fecha.setText(p.getFecha());
        datos.Hora.setText(p.getHora());
        Picasso.with(getContext()).load(p.getImagen()).into(datos.Imagen);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetalleOrden.class);
                i.putExtra("Orden",p);
                context.startActivity(i);

            }

        });
        return convertView;
    }

}
