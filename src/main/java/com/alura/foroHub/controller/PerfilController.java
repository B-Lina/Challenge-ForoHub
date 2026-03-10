package com.alura.foroHub.controller;

import com.alura.foroHub.domain.perfil.DatosDetallePerfil;
import com.alura.foroHub.domain.perfil.PerfilRepository;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Obtener detalle de un perfil con sus usuarios asociados
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallePerfil> detalle(@PathVariable Long id) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        var usuarios = usuarioRepository.findByPerfil(perfil);

        return ResponseEntity.ok(new DatosDetallePerfil(perfil, usuarios));
    }

    // 2. Listar todos los perfiles disponibles
    @GetMapping
    public ResponseEntity listarPerfiles() {
        var perfiles = perfilRepository.findAll();
        return ResponseEntity.ok(perfiles);
    }
}