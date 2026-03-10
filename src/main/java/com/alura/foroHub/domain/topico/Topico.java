package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.curso.Curso;
import com.alura.foroHub.domain.respuesta.Respuesta;
import com.alura.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.NO_RESPONDIDO;
    }
}