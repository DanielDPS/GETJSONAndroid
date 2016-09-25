package com.example.dany.gamaslt;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dany on 15/04/2016.
 */
public class Estatus implements Serializable{

    @SerializedName("estatus")
    private  String estatus;
    @SerializedName("cumplido")
    private  int cumplido;
    @SerializedName("fecha")
    private  String fecha;
    @SerializedName("hora")
    private  String hora;


    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getCumplido() {
        return cumplido;
    }

    public void setCumplido(int cumplido) {
        this.cumplido = cumplido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
