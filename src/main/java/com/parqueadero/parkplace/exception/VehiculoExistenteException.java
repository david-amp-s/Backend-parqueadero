package com.parqueadero.parkplace.exception;

public class VehiculoExistenteException extends RuntimeException {
    public VehiculoExistenteException() {
        super("El Vehiculo ya se encuentra registrado");
    }
}
