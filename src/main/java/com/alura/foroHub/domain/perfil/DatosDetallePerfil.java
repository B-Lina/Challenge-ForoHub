package com.alura.foroHub.domain.perfil;

public record DatosDetallePerfil(Long id, String nombrePerfil) {
    public DatosDetallePerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombrePerfil());
    }
}
