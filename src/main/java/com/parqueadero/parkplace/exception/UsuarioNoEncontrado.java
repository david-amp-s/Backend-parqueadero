package com.parqueadero.parkplace.exception;

public class UsuarioNoEncontrado extends RuntimeException {
    public UsuarioNoEncontrado() {
        super("Usuario no encontrado");
    }
}
