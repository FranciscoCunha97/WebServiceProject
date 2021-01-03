package io.javabrains.proesof.services;



import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface ProjetoService
{
    public List<Projeto> findAll();
    Optional<Projeto> findById(Long id);
    Optional<Projeto> createTarefaAoProjeto (Tarefa tarefa, Long projetoId);


}
