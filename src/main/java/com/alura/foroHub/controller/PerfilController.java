package com.alura.foroHub.controller;

import com.alura.foroHub.domain.perfil.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("perfiles")

public class PerfilController {
    @Autowired
    private PerfilRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPerfil datos, UriComponentsBuilder uriBuilder){
        var perfil = new Perfil(datos.nombrePerfil());
        repository.save(perfil);

        var uri = uriBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(perfil);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPerfil>> listar(@PageableDefault(size = 10, sort = {"nombrePerfil"}) Pageable paginacion) {
        // Asumiendo que tu repositorio tiene findAll() o una variante filtrada
        var page = repository.findAll(paginacion).map(DatosListaPerfil::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionPerfil datos) {
        var perfil = repository.getReferenceById(datos.id());
        perfil.actualizarNombrePerfil(datos.nombrePerfil());

        return ResponseEntity.ok(new DatosDetallePerfil(perfil));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var perfil = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallePerfil(perfil));
    }

}
