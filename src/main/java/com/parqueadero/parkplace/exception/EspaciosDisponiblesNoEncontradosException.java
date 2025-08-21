package com.parqueadero.parkplace.exception;

public class EspaciosDisponiblesNoEncontradosException extends RuntimeException {
    public EspaciosDisponiblesNoEncontradosException() {
        super("No se encuentran espacios disponibles");
    }
}
