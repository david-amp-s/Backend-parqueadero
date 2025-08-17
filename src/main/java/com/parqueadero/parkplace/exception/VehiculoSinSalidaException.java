package com.parqueadero.parkplace.exception;

public class VehiculoSinSalidaException extends RuntimeException {
    public VehiculoSinSalidaException() {
        super("Vehiculo sin salida registrada");
    }
}
