package com.alura.foroHub.controller;


import com.alura.foroHub.domain.curso.*;
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
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleCurso> registrar(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(datos.nombre(), datos.categoria());
        repository.save(curso);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        var page = repository.findAll(paginacion).map(DatosListaCurso::new);
        return ResponseEntity.ok(page);
    }
}
