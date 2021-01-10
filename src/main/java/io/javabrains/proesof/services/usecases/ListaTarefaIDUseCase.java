package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.ProjetoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListaTarefaIDUseCase {
    private TarefaRepository tarefaRepository;

    @Autowired
    public ListaTarefaIDUseCase(TarefaRepository tarefaRepository)
    {
        this.tarefaRepository = tarefaRepository;
    }


    public Optional<Tarefa> findById(Long id)
    {
        return tarefaRepository.findById(id);
    }
}
