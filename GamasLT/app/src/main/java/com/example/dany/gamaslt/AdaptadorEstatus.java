package com.example.dany.gamaslt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dany on 20/04/2016.
 */
public class AdaptadorEstatus extends ArrayAdapter<Estatus>{


    static  class  OEstatus{
        TextView estatus;
        TextView Fecha;
        TextView Hora;
    }

    public  AdaptadorEstatus(Context context, ArrayList<Estatus> lista){
        super(context, R.layout.orden_estatus,lista);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Estatus estatus = getItem(position);
        OEstatus estatus1 ;

        if (convertView == null)
        {
            estatus1 = new OEstatus();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.orden_estatus,parent,false);
            estatus1.estatus = (TextView)convertView.findViewById(R.id.txtEstatus);
            estatus1.Fecha= (TextView)convertView.findViewById(R.id.txtFecha);
            estatus1.Hora= (TextView)convertView.findViewById(R.id.txtHora);
            convertView.setTag(estatus1);
        }
        else
        {
            estatus1= (OEstatus)convertView.getTag();
        }
        estatus1.estatus.setText(estatus.getEstatus());
        estatus1.Fecha.setText(estatus.getFecha());
        estatus1.Hora.setText(estatus.getHora());
        return convertView;
    }
}
