package com.parqueadero.parkplace.exception;

public class FormapagoNoEncontrada extends RuntimeException {
    public FormapagoNoEncontrada() {
        super("Forma de pago no encontrada");
    }
}
