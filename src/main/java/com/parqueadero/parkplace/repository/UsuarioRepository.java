package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}
