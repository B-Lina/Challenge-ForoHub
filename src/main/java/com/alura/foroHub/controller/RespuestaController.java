package com.alura.foroHub.controller;

import com.alura.foroHub.domain.respuesta.*;
import com.alura.foroHub.domain.topico.Topico;
import com.alura.foroHub.domain.topico.TopicoRepository;
import com.alura.foroHub.domain.usuario.Usuario;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
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
@RequestMapping("/topico")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/respuestas") // Completa la ruta: /topicos/{id}/respuestas
    public ResponseEntity registrarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosRespuesta datos) {
        // Tu lógica aquí
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> listadoRespuestas(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosDetalleRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> detallarRespuesta(@PathVariable Long id) {
        Optional<Respuesta> respuestaOpcional = respuestaRepository.findById(id);
        if (respuestaOpcional.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleRespuesta(respuestaOpcional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> actualizarRespuesta(
            @RequestBody @Valid DatosActualizacionRespuesta datos, @PathVariable Long id) {
        Optional<Respuesta> respuestaOpcional = respuestaRepository.findById(id);
        if (respuestaOpcional.isPresent()) {
            Respuesta respuesta = respuestaOpcional.get();
            respuesta.actualizarInformacion(datos);
            return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionRespuesta datos) {
        var respuesta = respuestaRepository.getReferenceById(datos.id());
        respuesta.actualizarInformacion(datos);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        Optional<Respuesta> respuesta = respuestaRepository.findById(id);
        if (respuesta.isPresent()) {
            respuestaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
