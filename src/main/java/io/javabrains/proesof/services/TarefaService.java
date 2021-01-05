package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaService {
    List<Tarefa> findAll();
    Optional<Tarefa> findById(Long id);
    Optional<Tarefa> createTarefa(Tarefa tarefa);
}
