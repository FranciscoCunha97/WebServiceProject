package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CriaTarefaUseCase {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public CriaTarefaUseCase(TarefaRepository tarefaRepository)
    {
        this.tarefaRepository = tarefaRepository;
    }

    public Optional<Tarefa> createTarefa(Tarefa tarefa)
    {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findByNome(tarefa.getNome());
        if(optionalTarefa.isEmpty())
            return Optional.of(tarefaRepository.save(tarefa));
        return Optional.empty();
    }
}
