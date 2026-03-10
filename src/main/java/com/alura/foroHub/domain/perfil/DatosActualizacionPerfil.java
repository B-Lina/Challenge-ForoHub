package com.alura.foroHub.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPerfil(
        @NotNull Long id,
        String nombrePerfil
) {
}
