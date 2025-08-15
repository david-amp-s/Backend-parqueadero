package com.parqueadero.parkplace.security.dto;

public record RespuestaLoginDto(
                String tokenJwt,
                Long idUser,
                String rol) {

}
