package com.alura.foroHub.domain.usuario;

import org.antlr.v4.runtime.misc.NotNull;

public record DatosActualizacionUsuario (
        @NotNull Long id,
        String nombre,
        String email,
        @NotNull String perfiles) {
}
