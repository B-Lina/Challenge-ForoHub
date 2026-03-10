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
@EqualsAndHashCode(of="id")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    @OneToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfiles ;


    public Usuario(DatosUsuario datosUsuario, Perfil perfiles) {
        this.id = null;
        this.nombre = datosUsuario.nombre();
        this.email = datosUsuario.email();
        this.contrasena = datosUsuario.contrasena();
        this.perfiles = perfiles;
    }

    public void actualizarInformacion(DatosActualizacionUsuario datos){
        if(datos.nombre()!=null){
            this.nombre = datos.nombre();
        }
        if(datos.email()!=null){
            this.email = datos.email();
        }

    }


}

