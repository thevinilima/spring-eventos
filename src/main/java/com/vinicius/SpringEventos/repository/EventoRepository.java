package com.vinicius.SpringEventos.repository;

import com.vinicius.SpringEventos.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, String> {
    Evento findByCodigo(long codigo);
}
