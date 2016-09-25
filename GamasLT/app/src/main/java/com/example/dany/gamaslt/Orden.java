package com.example.dany.gamaslt;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dany on 03/04/2016.
 */
public class Orden implements Serializable{


    @SerializedName("numero")
    private   int numero;
    @SerializedName("operacion")
    private   String operacion;
    @SerializedName("tipoUnidad")
    private   String tipoUnidad;
    @SerializedName("unidad")
    private   String unidad;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("hora")
    private String hora;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("detalle")
    private  Detalle detalle ;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setDetalle(Detalle detail){
        detalle = detail;
    }
    public Detalle getDetalle(){
        return detalle;
    }
}
