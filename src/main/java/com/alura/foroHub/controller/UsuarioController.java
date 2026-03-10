package com.alura.foroHub.controller;

import com.alura.foroHub.domain.perfil.NombrePerfil;
import com.alura.foroHub.domain.perfil.Perfil;
import com.alura.foroHub.domain.perfil.PerfilRepository;
import com.alura.foroHub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriBuilder) {
        System.out.println("Datos recibidos: " + datos);
        NombrePerfil perfilEnum = NombrePerfil.valueOf(datos.nombrePerfil());
        // 1. Buscamos el perfil por nombre (ej: "USUARIO")
        var perfil = perfilRepository.findByNombrePerfil(perfilEnum)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado en la base de datos"));

        var usuario = new Usuario(datos, perfil);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuarios>> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion) {
        var page = usuarioRepository.findAll(paginacion).map(DatosListaUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionUsuario datos) {
        var usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizarInformacion(datos);

        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminar();

        return ResponseEntity.noContent().build();
    }


}