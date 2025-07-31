package com.parqueadero.parkplace.exception;

public class TipoVehiculoNoRegistrado extends RuntimeException {
    public TipoVehiculoNoRegistrado() {
        super("Tipo de vehiculo no registrado");
    }
}
