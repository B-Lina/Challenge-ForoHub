package com.alura.foroHub.domain.perfil;

public record DatosListaPerfil(Long id, String nombrePerfil) {
    public DatosListaPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombrePerfil());
    }
}
