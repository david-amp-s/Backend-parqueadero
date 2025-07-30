package com.parqueadero.parkplace.exception;

public class EspacioNoEncontrado extends RuntimeException {
    public EspacioNoEncontrado() {
        super("Espacio no encontrado");
    }
}
