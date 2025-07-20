package com.parqueadero.parkplace.exception;

public class RolNoEncontrado extends RuntimeException {
    public RolNoEncontrado(String rol) {
        super("El rol : " + rol + " No fue encontrado");
    }
}
