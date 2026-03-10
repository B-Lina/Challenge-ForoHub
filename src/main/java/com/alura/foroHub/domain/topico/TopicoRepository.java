package com.alura.foroHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    @Query("SELECT t FROM Topico t JOIN FETCH t.autor JOIN FETCH t.curso")
    Page<Topico> findAll(Pageable paginacion);
}