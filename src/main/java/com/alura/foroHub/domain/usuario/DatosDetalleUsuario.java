package com.alura.foroHub.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email,
        String nombrePerfil)
{
    public DatosDetalleUsuario(Usuario usuario) {
        this(usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil() != null ? usuario.getPerfil().getNombrePerfil().name() : null);
    }
}
