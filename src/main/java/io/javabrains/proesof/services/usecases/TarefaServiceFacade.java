package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TarefaServiceFacade implements TarefaService {

    private final CriaTarefaUseCase criaTarefaUseCase;
    private final AdicionaEmpregadoATarefaUseCase adicionaEmpregadoATarefaUseCase;
    private final ListaTodasTarefasUseCase listaTodasTarefasUseCase;
    private final ListaTarefaIDUseCase listaTarefaIDUseCase;


    @Autowired
    public TarefaServiceFacade(CriaTarefaUseCase criaTarefaUseCase, AdicionaEmpregadoATarefaUseCase adicionaEmpregadoATarefaUseCase, ListaTodasTarefasUseCase listaTodasTarefasUseCase, ListaTarefaIDUseCase listaTarefaIDUseCase) {
        this.criaTarefaUseCase = criaTarefaUseCase;
        this.adicionaEmpregadoATarefaUseCase = adicionaEmpregadoATarefaUseCase;
        this.listaTodasTarefasUseCase = listaTodasTarefasUseCase;
        this.listaTarefaIDUseCase = listaTarefaIDUseCase;
    }

    @Override
    public List<Tarefa> findAll() {
        return listaTodasTarefasUseCase.findAll();
    }

    @Override
    public Optional<Tarefa> findById(Long id) {
        return listaTarefaIDUseCase.findById(id);
    }

    @Override
    public Optional<Tarefa> createTarefa(Tarefa tarefa) {
        return criaTarefaUseCase.createTarefa(tarefa);
    }

    @Override
    public Optional<Tarefa> adicionaEmpregadoATarefa(Long tarefaId, Empregado empregado) {
        return adicionaEmpregadoATarefaUseCase.adicionaEmpregadoATarefa(tarefaId,empregado);
    }
}
