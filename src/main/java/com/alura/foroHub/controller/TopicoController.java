package com.alura.foroHub.controller;

import com.alura.foroHub.domain.curso.Curso;
import com.alura.foroHub.domain.curso.CursoRepository;
import com.alura.foroHub.domain.topico.*;
import com.alura.foroHub.domain.usuario.Usuario;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriBuilder) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> autor = usuarioRepository.findById(datos.idAutor());
        Optional<Curso> curso = cursoRepository.findById(datos.idCurso());

        if (autor.isEmpty() || curso.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), autor.get(), curso.get());
        topicoRepository.save(topico);

        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listadoTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOpcional = topicoRepository.findById(id);
        if (topicoOpcional.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topicoOpcional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional // IMPORTANTE: Agrega esta anotación para que los cambios se guarden automáticamente
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(@RequestBody @Valid DatosActualizacionTopico datos, @PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id); // O findById().get()

        // Si envían un ID de curso, debemos buscarlo primero
        Curso curso = null;
        if (datos.idCurso() != null) {
            curso = cursoRepository.getReferenceById(datos.idCurso());
        }

        topico.actualizarInformacion(datos.titulo(), datos.mensaje(), datos.status(), curso);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
