package com.parqueadero.parkplace.exception;

public class VehiculoConSalidaPendienteException extends RuntimeException {
    public VehiculoConSalidaPendienteException() {
        super("El vehiculo tiene una factura pendiente");
    }
}
