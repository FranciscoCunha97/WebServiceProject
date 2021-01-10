package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistaPercentualUseCase {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public RegistaPercentualUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    public Optional<Tarefa> registaPercentual (Long idTarefa, int percentual)
    {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(idTarefa);
        if (optionalTarefa.isPresent())
        {
            Tarefa tarefa = optionalTarefa.get();
            if(tarefa.getTarefaPlaneamento().getPercentualConclusao()<=100 && percentual>0)
            {
                tarefa.getTarefaPlaneamento().setPercentualConclusao(percentual);
                return Optional.of(tarefa);
            }
        }
        return Optional.empty();
    }

}
