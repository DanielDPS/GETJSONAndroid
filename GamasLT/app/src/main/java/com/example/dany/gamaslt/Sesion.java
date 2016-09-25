package com.example.dany.gamaslt;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Dany on 03/04/2016.
 */
public class Sesion {

    //esta
    private static   Usuario usuario;
    private static ArrayList<Usuario> listado;
    private  static  Sesion _Instancia  = null;

    public Usuario getUsuario() {
        return usuario;
    }
    public  void AgregarUsuario(Usuario nuevo){
        listado.add(nuevo);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Usuario> getListado() {
        return listado;
    }
    public  static  Sesion getSesion(){
        if(_Instancia==null){
            _Instancia= new Sesion();
            listado  = new ArrayList<>();
        }
        return  _Instancia;
    }
    public static void CloseSession(){
        _Instancia = null;
    }
}
