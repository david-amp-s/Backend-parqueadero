package com.parqueadero.parkplace.exception;

public class TarifaNoEncontradException extends RuntimeException {
    public TarifaNoEncontradException() {
        super("Tarifa No encontrada");
    }
}
