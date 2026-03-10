package com.alura.foroHub.domain.topico;

public record DatosActualizacionTopico(
        String titulo,
        String mensaje,
        StatusTopico status,
        Long idCurso) {
}
