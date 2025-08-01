package com.parqueadero.parkplace.exception;

public class FacturaNoEncontradaException extends RuntimeException {
    public FacturaNoEncontradaException() {
        super("Factura no encotrada");
    }
}
