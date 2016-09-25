package com.example.dany.gamaslt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dany on 21/04/2016.
 */
public class JsonParser implements  Serializable  {


    private  static  JsonParser instancia;

    public  static JsonParser getInstancia(){
        if (instancia == null)
            instancia= new JsonParser();
        return instancia;
    }

    private  static  ArrayList<Orden> Ordenes;
    private JsonParser(){
        Ordenes  = new ArrayList<>();
    }

    public static void parseJson(String jsonString)
    {
        try
        {
            JSONObject user,DETALLE,OEstatus;
            JSONObject json = new JSONObject(jsonString);
            JSONArray userdetails = json.getJSONArray("Ordenes");
            JSONArray estatus ;

            Ordenes.clear();
            for (int i=0; i<userdetails.length(); i++)
            {
                user = userdetails.getJSONObject(i);
                int numero = user.getInt("numero");
                String operacion = user.getString("operacion");
                String tipoUnidad = user.getString("tipoUnidad");
                String unidad = user.getString("unidad");
                String fecha = user.getString("fecha");
                String hora = user.getString("hora");
                String imagen =  user.getString("imagen");
                DETALLE = user.getJSONObject("detalle");

                Orden orden = new Orden();
                orden.setNumero(numero);
                orden.setOperacion(operacion);
                orden.setTipoUnidad(tipoUnidad);
                orden.setUnidad(unidad);
                orden.setFecha(fecha);
                orden.setHora(hora);
                orden.setImagen(imagen);



                DETALLE = user.getJSONObject("detalle");
                String caja = DETALLE.getString("caja");
                String placas = DETALLE.getString("placas");
                String Unidad = DETALLE.getString("unidad");
                String origen = DETALLE.getString("origen");
                String destino = DETALLE.getString("destino");
                String Fecha = DETALLE.getString("fecha_requerido");
                String Hora = DETALLE.getString("hora_requerido");


                Detalle detalle = new Detalle();
                detalle.set_Caja(caja);
                detalle.set_placa(placas);
                detalle.set_Unidad(Unidad);
                detalle.set_Origen(origen);
                detalle.set_Destino(destino);
                detalle.set_FechaRequerido(Fecha);
                detalle.set_Hora(Hora);
                //el detalle


                estatus = DETALLE.getJSONArray("estatus");
                ArrayList<Estatus> estatuses = new ArrayList<>();
                for (int j =0; j<estatus.length(); j++){

                    OEstatus = estatus.getJSONObject(j);
                    String Estatus= OEstatus.getString("estatus");
                    int Cumplido = OEstatus.getInt("cumplido");
                    String FechaEstatus = OEstatus.getString("fecha");
                    String HoraEstatus = OEstatus.getString("hora");

                    Estatus estatusOrden = new Estatus();
                    estatusOrden.setEstatus(Estatus);
                    estatusOrden.setCumplido(Cumplido);
                    estatusOrden.setFecha(FechaEstatus);
                    estatusOrden.setHora(HoraEstatus);
                    estatuses.add(estatusOrden);
                }
                detalle.setEstatusArrayList(estatuses);
                orden.setDetalle(detalle);
                Ordenes.add(orden);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Orden> getOrdeers(){
        return Ordenes;
    }

}
