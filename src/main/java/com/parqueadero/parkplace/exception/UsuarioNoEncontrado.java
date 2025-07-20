package com.parqueadero.parkplace.exception;

public class UsuarioNoEncontrado extends RuntimeException {
    public UsuarioNoEncontrado(String correo) {
        super("El correo : " + correo + " No se encontro");
    }
}
