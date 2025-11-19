
package com.parqueadero.parkplace.exception;

public class TipoClienteNoEncontradoException extends RuntimeException {
    public TipoClienteNoEncontradoException() {
        super("Tipo de cliente no encontrado");
    }
}
