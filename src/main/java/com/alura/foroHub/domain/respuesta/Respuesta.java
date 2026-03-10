package com.alura.foroHub.domain.respuesta;

import com.alura.foroHub.domain.perfil.Perfil;
import com.alura.foroHub.domain.topico.Topico;
import com.alura.foroHub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table (name = "respuestas")
@Entity (name = "respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private String topico;

    private String fechaCreacion = String.valueOf(LocalDateTime.now());

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario ;

    private Boolean solucion = false;


    public Respuesta(String mensaje, Usuario usuario, Topico topico) {
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.topico = topico.toString();
        this.fechaCreacion = String.valueOf(LocalDateTime.now());
        this.solucion = false;
    }
}

