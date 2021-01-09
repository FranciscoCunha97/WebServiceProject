package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface ProjetoService
{
    List<Projeto> findAll();
    Optional<Projeto> findById(Long id);
    Optional<Projeto> createProjeto(Projeto projeto);
    public Optional<Projeto> createTarefaAoProjeto(Long projetoId,Tarefa tarefa);
}
