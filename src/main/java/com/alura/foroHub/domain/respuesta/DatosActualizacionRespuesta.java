package com.alura.foroHub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionRespuesta(
        @NotNull Long id,
        String mensaje,
        Boolean solucion) {
}
