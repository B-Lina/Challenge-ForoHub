package com.alura.foroHub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuesta(
        @NotNull String mensaje,
        @NotNull String topico,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaCreacion,
        @NotNull String autor,
        @NotNull String solucion
) {
}
