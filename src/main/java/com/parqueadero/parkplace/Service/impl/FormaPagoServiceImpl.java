package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.FormaPagoService;
import com.parqueadero.parkplace.dto.FormaPagoCreateDto;
import com.parqueadero.parkplace.dto.FormaPagoDto;
import com.parqueadero.parkplace.exception.FormapagoNoEncontrada;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.repository.FormaPagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormaPagoServiceImpl implements FormaPagoService {

    private final FormaPagoRepository formaPagoRepository;

    @Override
    public FormaPagoDto ingresarFormaPago(FormaPagoCreateDto dto) {
        FormaPago formaPago = FormaPago.builder()
                .descripcion(dto.detalle())
                .build();
        formaPagoRepository.save(formaPago);
        return new FormaPagoDto(formaPago.getId(), formaPago.getDescripcion());
    }

    @Override
    public void eliminarFormaPago(Long id) {
        FormaPago formaPago = formaPagoRepository.findById(id).orElseThrow(() -> new FormapagoNoEncontrada());
        formaPagoRepository.delete(formaPago);
    }

    @Override
    public List<FormaPagoDto> listaFormaPago() {
        return formaPagoRepository.findAll().stream()
                .map(f -> new FormaPagoDto(f.getId(), f.getDescripcion())).toList();
    }

}
