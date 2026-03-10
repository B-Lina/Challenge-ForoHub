package com.alura.foroHub.controller;

import com.alura.foroHub.infra.Autentication.DatosAutenticacion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        // Aquí es donde Spring Security validará las credenciales
        var authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(), datosAutenticacion.contrasena());
        //var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Si todo sale bien, aquí generarías y retornarías tu token JWT
        return ResponseEntity.ok().build();
    }
    /*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }*/
}