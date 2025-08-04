package com.parqueadero.parkplace.Service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.DetallePagoService;
import com.parqueadero.parkplace.dto.DetallePagoCreateDto;
import com.parqueadero.parkplace.dto.DetallePagoDto;
import com.parqueadero.parkplace.exception.FacturaNoEncontradaException;
import com.parqueadero.parkplace.exception.FormapagoNoEncontrada;
import com.parqueadero.parkplace.exception.PagoExcedidoException;
import com.parqueadero.parkplace.model.DetallePago;
import com.parqueadero.parkplace.model.Factura;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.repository.DetallePagoRepository;
import com.parqueadero.parkplace.repository.FacturaRepository;
import com.parqueadero.parkplace.repository.FormaPagoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePagoServiceImpl implements DetallePagoService {
    private final DetallePagoRepository detallePagoRepository;
    private final FacturaRepository facturaRepository;
    private final FormaPagoRepository formaPagoRepository;

    private DetallePagoDto conversorDto(DetallePago dto) {
        return new DetallePagoDto(dto.getId(), dto.getFactura().getId(), dto.getFormaPago().getDescripcion(),
                dto.getMonto());
    }

    @Override
    public DetallePagoDto registrarDetallePago(DetallePagoCreateDto dto) {
        Factura factura = facturaRepository.findById(dto.factura_id())
                .orElseThrow(() -> new FacturaNoEncontradaException());
        FormaPago formaPago = formaPagoRepository.findById(dto.formaPago_id())
                .orElseThrow(() -> new FormapagoNoEncontrada());

        BigDecimal totalPagado = detallePagoRepository.findByFacturaId(dto.factura_id())
                .stream()
                .map(DetallePago::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal nuevoTotal = totalPagado.add(dto.monto());
        if (nuevoTotal.compareTo(factura.getTotal()) > 0) {
            throw new PagoExcedidoException();
        }

        DetallePago nuevoPago = DetallePago.builder()
                .factura(factura)
                .formaPago(formaPago)
                .monto(dto.monto())
                .build();

        detallePagoRepository.save(nuevoPago);
        return conversorDto(nuevoPago);
    }

    @Override
    @Transactional
    public List<DetallePagoDto> registrarVariosPagos(List<DetallePagoCreateDto> pagos) {
        if (pagos.isEmpty()) {
            throw new IllegalArgumentException("Debe registrar al menos un pago.");
        }

        Long facturaId = pagos.get(0).factura_id(); // asumimos que todos los pagos son de la misma factura
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new FacturaNoEncontradaException());

        BigDecimal totalPrevio = detallePagoRepository.findByFacturaId(factura.getId()).stream()
                .map(DetallePago::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalNuevo = pagos.stream()
                .map(DetallePagoCreateDto::monto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalFinal = totalPrevio.add(totalNuevo);

        if (totalFinal.compareTo(factura.getTotal()) > 0) {
            throw new RuntimeException("La suma de los pagos excede el valor total de la factura.");
        }

        List<DetallePagoDto> resultado = new ArrayList<>();
        for (DetallePagoCreateDto dto : pagos) {
            FormaPago metodo = formaPagoRepository.findById(dto.formaPago_id())
                    .orElseThrow(() -> new FormapagoNoEncontrada());

            DetallePago nuevo = DetallePago.builder()
                    .factura(factura)
                    .formaPago(metodo)
                    .monto(dto.monto())
                    .build();

            detallePagoRepository.save(nuevo);

            resultado.add(conversorDto(nuevo));
        }

        return resultado;
    }

    @Override
    public List<DetallePagoDto> obtenerPagosPorFactura(Long facturaId) {
        return detallePagoRepository.findByFacturaId(facturaId)
                .stream().map(d -> conversorDto(d)).toList();
    }

    @Override
    public DetallePagoDto obtenerDetallePago(Long id) {
        DetallePago detallePago = detallePagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
        return conversorDto(detallePago);
    }

}
