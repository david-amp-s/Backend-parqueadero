package com.parqueadero.parkplace.exception;

public class SalidaNoEncontrada extends RuntimeException {
    public SalidaNoEncontrada() {
        super("Salida no encontrada");
    }
}
