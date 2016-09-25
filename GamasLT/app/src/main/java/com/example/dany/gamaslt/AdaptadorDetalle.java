package com.example.dany.gamaslt;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Dany on 16/04/2016.
 */
public class AdaptadorDetalle extends ArrayAdapter<Detalle> {


    public  static  class  DetalleOrden
    {
            TextView Caja;
            TextView Placas;
            TextView Unidad;
            TextView Origen;
            TextView Destino;
            TextView Fecha;
            TextView Hora;
    }


    public  AdaptadorDetalle(Context context, ArrayList<Detalle> detalles){

        super(context,R.layout.orden_detalle,detalles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Detalle detalle = getItem(position);
        DetalleOrden detalleOrden;

        if  (convertView == null)
        {
            detalleOrden = new DetalleOrden();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.orden_detalle,parent, false);
            detalleOrden.Caja = (TextView)convertView.findViewById(R.id.txtCaja);
            detalleOrden.Placas = (TextView)convertView.findViewById(R.id.txtPlacas);
            detalleOrden.Unidad = (TextView)convertView.findViewById(R.id.txtUnidad);
            detalleOrden.Origen = (TextView)convertView.findViewById(R.id.txtPlacas);
            detalleOrden.Destino = (TextView)convertView.findViewById(R.id.txtDestino);
            detalleOrden.Fecha = (TextView)convertView.findViewById(R.id.txtFecha);
            detalleOrden.Hora= (TextView)convertView.findViewById(R.id.txtHora);
            convertView.setTag(detalleOrden);

        }else {
            detalleOrden= (DetalleOrden) convertView.getTag();
        }
        detalleOrden.Caja.setText(detalle.get_Caja());
        detalleOrden.Placas.setText(detalle.get_placa());
        detalleOrden.Unidad.setText(detalle.get_Unidad());
        detalleOrden.Origen.setText(detalle.get_Origen());
        detalleOrden.Destino.setText(detalle.get_Destino());
        detalleOrden.Fecha.setText(detalle.get_FechaRequerido());
        detalleOrden.Hora.setText(detalle.get_Hora());
        return  convertView;



    }
}
