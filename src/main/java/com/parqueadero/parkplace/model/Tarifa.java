package com.parqueadero.parkplace.model;

import com.parqueadero.parkplace.enums.TipoVehiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tarifas")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Tarifa {
    Long id;

    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipoVehiculo;

    private Integer valorHora;

    private Integer valorMinuto;

    private Integer ValorTarifaFija;

    private boolean TarifaFija;

}
