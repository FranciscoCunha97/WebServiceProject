package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface ProjetoService
{
    List<Projeto> findAll();
    Optional<Projeto> findById(Long id);
    Optional<Projeto> createTarefaAoProjeto (Projeto projeto, Long projetoId);
}
