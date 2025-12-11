package com.parqueadero.parkplace.dto;

public record TarifaDto(
                Long id,
                String tipovehiculo,
                String tipoCliente,
                Integer valorMinuto,
                Integer valorTarifaFija) {

}
