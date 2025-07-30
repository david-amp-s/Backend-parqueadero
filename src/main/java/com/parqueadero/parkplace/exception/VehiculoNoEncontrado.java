package com.parqueadero.parkplace.exception;

public class VehiculoNoEncontrado extends RuntimeException {
    public VehiculoNoEncontrado() {
        super("Vehiculo no encontrado");
    }
}
