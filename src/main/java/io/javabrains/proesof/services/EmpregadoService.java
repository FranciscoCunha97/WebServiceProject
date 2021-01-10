package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface EmpregadoService
{

    List<Empregado> findAllEmpregados();
    Optional<Empregado> findById(Long id);
    List<Tarefa> consultarTarefa(Long idEmpregado);
    Optional<Empregado> createEmpregado(Empregado converter);
    Optional<Empregado> createTarefa(Tarefa tarefa, Long empregadoId);
    Optional<Projeto> createProjeto(Projeto projeto, Long clienteId);
    Optional<Tarefa> registaPercentual (Long idTarefa, int percentual);
    Optional<Tarefa> marcaExecucaoJaRealizadas (Long idTarefa);
}
