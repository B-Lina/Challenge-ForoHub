package com.alura.foroHub.domain.usuario;

import com.alura.foroHub.domain.perfil.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findByPerfil(Perfil perfil);

}

