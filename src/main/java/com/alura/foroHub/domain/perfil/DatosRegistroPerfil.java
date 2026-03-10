package com.alura.foroHub.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(
        @NotBlank String nombrePerfil
) {
}
