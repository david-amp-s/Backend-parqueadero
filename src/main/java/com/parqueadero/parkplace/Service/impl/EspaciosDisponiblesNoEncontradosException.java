package com.parqueadero.parkplace.Service.impl;

public class EspaciosDisponiblesNoEncontradosException extends RuntimeException {
    public EspaciosDisponiblesNoEncontradosException() {
        super("No se encuentran espacios disponibles");
    }
}
