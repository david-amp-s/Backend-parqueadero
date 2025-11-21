package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarifaCreateDto(
                @NotBlank(message = "Ingrese un vehiculo valido") String vehiculo,
                @NotBlank(message = "Ingrese un cliente valido") String tipoCliente,
                @NotNull(message = "Ingrese un valor valido") int valorMinuto,
                @NotNull(message = "Ingrese un valor valido") int valorTarifaFija) {

}
