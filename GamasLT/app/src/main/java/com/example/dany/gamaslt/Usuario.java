package com.example.dany.gamaslt;

import java.io.Serializable;

/**
 * Created by Dany on 03/04/2016.
 */
public class Usuario implements Serializable {





    private  String _Usuario;
    private  String _Contra;
    private  String _Nombre;
    private  String _Apellidos;

    public String get_Usuario() {
        return _Usuario;
    }

    public void set_Usuario(String _Usuario) {
        this._Usuario = _Usuario;
    }

    public String get_Contra() {
        return _Contra;
    }

    public void set_Contra(String _Contra) {
        this._Contra = _Contra;
    }

    public String get_Nombre() {
        return _Nombre;
    }

    public void set_Nombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    public String get_Apellidos() {
        return _Apellidos;
    }

    public void set_Apellidos(String _Apellidos) {
        this._Apellidos = _Apellidos;
    }
}
