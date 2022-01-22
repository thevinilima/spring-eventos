package com.vinicius.SpringEventos.repository;

import com.vinicius.SpringEventos.model.Convidado;
import com.vinicius.SpringEventos.model.Evento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
    List<Convidado> findByEvento(Evento evento);
}
