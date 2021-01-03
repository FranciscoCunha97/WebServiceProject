package io.javabrains.proesof.services;


import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface EmpregadoService
{

    public List<Empregado> findAll();
    Optional<Empregado> findById(Long id);
    Optional<Tarefa> findTarefaById(Long id);
    Optional<Empregado> createEmpregado(Empregado converter);
    Optional<Tarefa> createTarefa(Tarefa tarefa, Long empregadoId);
    Optional<Projeto> createProjeto(Projeto projeto, Long clienteId);



}
