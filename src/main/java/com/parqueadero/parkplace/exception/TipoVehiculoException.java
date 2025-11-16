package com.parqueadero.parkplace.exception;

public class TipoVehiculoException extends RuntimeException {
    public TipoVehiculoException() {
        super("Tipo vehiculo no encontrado");
    }
}
