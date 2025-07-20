package com.parqueadero.parkplace.exception;

public class VehiculoNoEncontrado extends RuntimeException {
    public VehiculoNoEncontrado(String placa) {
        super("La placa :" + placa + " No fue encontrada");
    }
}
