package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaTodasTarefasUseCase {

    private TarefaRepository tarefaRepository;
    private EmpregadoRepository empregadoRepository;

    @Autowired
    public ListaTodasTarefasUseCase(TarefaRepository tarefaRepository)
    {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> findAll()
    {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefaRepository.findAll().forEach(tarefas::add);
        return tarefas;
    }
}
