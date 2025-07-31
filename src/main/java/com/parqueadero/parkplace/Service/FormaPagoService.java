package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.FormaPagoCreateDto;
import com.parqueadero.parkplace.dto.FormaPagoDto;

public interface FormaPagoService {
    FormaPagoDto ingresarFormaPago(FormaPagoCreateDto dto);

    void eliminarFormaPago(Long id);

    List<FormaPagoDto> listaFormaPago();
}
