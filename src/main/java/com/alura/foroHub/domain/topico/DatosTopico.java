package com.alura.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long cursoId,
        @NotNull Long autorId)
{
}
