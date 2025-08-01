package com.parqueadero.parkplace.exception;

public class PagoExcedidoException extends RuntimeException {
    public PagoExcedidoException() {
        super("El monto total excede el valor de la factura.");
    }
}
