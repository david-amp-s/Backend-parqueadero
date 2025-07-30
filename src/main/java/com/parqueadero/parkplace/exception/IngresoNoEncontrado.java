package com.parqueadero.parkplace.exception;

public class IngresoNoEncontrado extends RuntimeException {
    public IngresoNoEncontrado() {
        super("Ingreso no encontrado");
    }
}
