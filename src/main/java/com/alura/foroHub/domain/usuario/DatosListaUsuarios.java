package com.alura.foroHub.domain.usuario;

import com.alura.foroHub.domain.perfil.Perfil;

public record DatosListaUsuarios(
        Long id,
        String nombre,
        String email,
        String nombrePerfil) {
    public DatosListaUsuarios(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil() != null ? usuario.getPerfil().getNombrePerfil().name() : null);
    }
}
