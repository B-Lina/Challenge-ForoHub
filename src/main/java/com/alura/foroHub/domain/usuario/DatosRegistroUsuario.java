package com.alura.foroHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario (
        @NotBlank String nombre,
        @Email @NotBlank String email,
        @NotBlank String contrasena,
        @NotBlank String nombrePerfil
){
}
