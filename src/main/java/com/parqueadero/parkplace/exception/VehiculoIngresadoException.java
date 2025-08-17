package com.parqueadero.parkplace.exception;

public class VehiculoIngresadoException extends RuntimeException {
    public VehiculoIngresadoException() {
        super("El vehiculo ya esta ingresado");
    }
}
