package com.alura.foroHub.domain.perfil;

import com.alura.foroHub.domain.usuario.DatosListaUsuarios;

import java.util.List;

public record DatosDetallePerfil(
        NombrePerfil nombrePerfil,
        List<DatosListaUsuarios> usuarios
) {
    // Constructor que acepta la Entidad Perfil y transforma la lista de usuarios
    public DatosDetallePerfil(Perfil perfil, List<com.alura.foroHub.domain.usuario.Usuario> usuarios) {
        this(
                perfil.getNombrePerfil(),
                usuarios.stream().map(DatosListaUsuarios::new).toList()
        );
    }
}
