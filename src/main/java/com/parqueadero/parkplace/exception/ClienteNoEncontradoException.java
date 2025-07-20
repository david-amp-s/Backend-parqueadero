package com.parqueadero.parkplace.exception;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String cedula) {
        super("Cliente no encontrado c.c. : " + cedula);
    }
}
