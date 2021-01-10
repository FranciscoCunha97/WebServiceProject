package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MarcaExecucaoRealizadasUseCase {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public MarcaExecucaoRealizadasUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    public Optional<Tarefa> marcaExecucaoJaRealizadas (Long idTarefa)
    {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(idTarefa);
        if (optionalTarefa.isPresent())
        {
            Tarefa tarefa = optionalTarefa.get();
            if (tarefa.getTarefaPlaneamento().getPercentualConclusao()==100)
                return Optional.of(tarefa);
        }
        return Optional.empty();
    }
}
