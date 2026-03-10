package com.alura.foroHub.domain.perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // Busca específicamente por el Enum NombrePerfil
    Optional<Perfil> findByNombrePerfil(NombrePerfil nombrePerfil);
}