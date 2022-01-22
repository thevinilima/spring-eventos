package com.vinicius.SpringEventos.repository;

import com.vinicius.SpringEventos.model.Convidado;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
}
