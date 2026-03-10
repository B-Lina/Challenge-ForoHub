package com.alura.foroHub.controller;

import com.alura.foroHub.domain.perfil.Perfil;
import com.alura.foroHub.domain.perfil.PerfilRepository;
import com.alura.foroHub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosUsuario datos, UriComponentsBuilder uriBuilder) {
        var perfil = new Perfil(datos.perfiles());
        perfilRepository.save(perfil);

        // 2. Crear el usuario y asociar el  perfil guardado
        var usuario = new Usuario(datos, perfil);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }
}