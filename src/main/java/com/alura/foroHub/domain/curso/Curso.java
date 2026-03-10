package com.alura.foroHub.domain.curso;

import com.alura.foroHub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity (name = "curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;

    // Relación bidireccional: un curso tiene muchos tópicos
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Topico> topicos = new ArrayList<>();

    // Constructor simple
    public Curso(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }
}