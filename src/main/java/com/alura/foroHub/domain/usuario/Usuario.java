package com.alura.foroHub.domain.usuario;

import com.alura.foroHub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    @Column(unique = true)
    private String email;
    private String contrasena;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;


    public Usuario(DatosRegistroUsuario datosUsuario, Perfil perfil) {
        this.id = null;
        this.activo = true;
        this.nombre = datosUsuario.nombre();
        this.email = datosUsuario.email();
        this.contrasena = datosUsuario.contrasena();
        this.perfil = perfil;
    }

    public void actualizarInformacion(DatosActualizacionUsuario datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.email() != null) {
            this.email = datos.email();
        }
    }
    public void actualizarPerfil(Perfil nuevoPerfil) {
        if (nuevoPerfil != null) {
            this.perfil = nuevoPerfil;
        }
    }

    public void eliminar() {
        this.activo = false;
    }

}
