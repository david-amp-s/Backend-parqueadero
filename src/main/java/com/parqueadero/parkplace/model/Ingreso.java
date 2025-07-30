package com.parqueadero.parkplace.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingresos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Vehiculo vehiculo;
    @ManyToOne
    private Espacio espacio;
    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;
}
