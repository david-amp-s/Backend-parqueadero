package com.parqueadero.parkplace.Service.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.parqueadero.parkplace.dto.FacturaDto;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.exception.FacturaNoEncontradaException;
import com.parqueadero.parkplace.model.Factura;
import com.parqueadero.parkplace.repository.FacturaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FacturaPDFService {
    private final FacturaRepository facturaRepository;

    public byte[] generarFactura(FacturaDto facturaDTO) throws IOException {

        Factura factura = facturaRepository.findById(facturaDTO.id())
                .orElseThrow(() -> new FacturaNoEncontradaException());

        String nombreCliente = factura.getSalida().getIngreso().getVehiculo().getCliente().getNombre();
        String placaVehiculo = factura.getSalida().getIngreso().getVehiculo().getPlaca();
        TipoVehiculo tipoVeiculo = factura.getSalida().getIngreso().getVehiculo().getTipoVehiculo();
        LocalDateTime fechaEntrada = factura.getSalida().getIngreso().getFechaIngreso();
        LocalDateTime fechaSalida = factura.getSalida().getFechaSalida();

        Duration duracion = Duration.between(fechaEntrada, fechaSalida);
        long horas = duracion.toHours();
        long minutos = duracion.toMinutesPart();
        String tiempoFormateado = horas + " horas " + minutos + " minutos";

        BigDecimal total = factura.getTotal();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titulo = new Paragraph("Factura ParkPlace", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Factura N°: " + facturaDTO.id()));
        document.add(new Paragraph(
                "Fecha: " + facturaDTO.fecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        document.add(new Paragraph("Cliente: " + nombreCliente));
        document.add(new Paragraph("Vehículo: " + placaVehiculo));
        document.add(new Paragraph("Tipo: " + tipoVeiculo));
        document.add(new Paragraph("Tiempo Estacionado: " + tiempoFormateado));
        document.add(new Paragraph("Total: $" + total));
        document.add(new Paragraph(" "));

        Paragraph gracias = new Paragraph("Gracias por usar ParkPlace 🚗");
        gracias.setAlignment(Element.ALIGN_CENTER);
        document.add(gracias);

        document.close();

        return outputStream.toByteArray();
    }
}
