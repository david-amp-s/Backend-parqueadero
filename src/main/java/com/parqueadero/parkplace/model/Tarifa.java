package com.parqueadero.parkplace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    private TipoVehiculoEnt tipoVehiculoEnt;

    private Integer valorHora;

    private Integer valorMinuto;

    private Integer valorTarifaFija;

    private boolean TarifaFija;

}
