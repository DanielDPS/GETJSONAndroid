package com.example.dany.gamaslt;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dany on 15/04/2016.
 */
public class Detalle implements Serializable {


    @SerializedName("caja")
    private String caja;
    @SerializedName("placas")
    private  String placas;
    @SerializedName("unidad")
    private  String unidad;
    @SerializedName("origen")
    private  String origen;
    @SerializedName("destino")
    private  String destino;
    @SerializedName("fecha_requerido")
    private  String fecha_requerido;
    @SerializedName("hora_requerido")
    private  String hora_requerido;
    private ArrayList<Estatus> estatusArrayList;

    public String get_Caja() {
        return caja;
    }

    public void set_Caja(String _Caja) {
        this.caja = _Caja;
    }

    public String get_placa() {
        return placas;
    }

    public void set_placa(String _placa) {
        this.placas = _placa;
    }

    public String get_Unidad() {
        return unidad;
    }

    public void set_Unidad(String _Unidad) {
        this.unidad = _Unidad;
    }

    public String get_Origen() {
        return origen;
    }

    public void set_Origen(String _Origen) {
        this.origen = _Origen;
    }

    public String get_Destino() {
        return destino;
    }

    public void set_Destino(String _Destino) {
        this.destino = _Destino;
    }

    public String get_FechaRequerido() {
        return fecha_requerido;
    }

    public void set_FechaRequerido(String _FechaRequerido) {
        this.fecha_requerido = _FechaRequerido;
    }
    public String get_Hora() {
        return hora_requerido;
    }

    public void set_Hora(String _Hora) {
        this.hora_requerido = _Hora;
    }
    public void setEstatusArrayList(ArrayList<Estatus> estatus){
        estatusArrayList = estatus;
    }
    public ArrayList<Estatus> getEstatusArrayList(){
        return estatusArrayList;
    }
}
