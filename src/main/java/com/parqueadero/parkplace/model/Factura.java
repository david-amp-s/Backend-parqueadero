package com.parqueadero.parkplace.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facturas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Salida salida;

    @ManyToOne
    private Usuario usuario;

    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "metodo_pago")
    private FormaPago metodoPago;

    private LocalDateTime fecha;
}
