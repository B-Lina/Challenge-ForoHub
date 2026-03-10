package com.alura.foroHub.domain.perfil;

import com.alura.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePerfil;

    @OneToOne(mappedBy = "perfil")
    private Usuario usuario;

    public Perfil(String nombrePerfil) {

        this.nombrePerfil = nombrePerfil;
    }

    public void actualizarNombrePerfil(String nombrePerfilModificado){
        if(nombrePerfilModificado != null){
            this.nombrePerfil = nombrePerfilModificado;
        }
    }
}



