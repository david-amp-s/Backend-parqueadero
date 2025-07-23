package com.parqueadero.parkplace.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.exception.UsuarioNoEncontrado;
import com.parqueadero.parkplace.model.Usuario;
import com.parqueadero.parkplace.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioNoEncontrado(email));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getContraseña(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre())));
    }

}
