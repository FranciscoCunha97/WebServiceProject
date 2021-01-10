package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdicionaEmpregadoATarefaUseCase {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public AdicionaEmpregadoATarefaUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Optional<Tarefa> adicionaEmpregadoATarefa(Long tarefaId, Empregado empregado) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(tarefaId);
        if(optionalTarefa.isPresent()){
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setEmpregado(empregado);
            return Optional.of(tarefa);
        }
        return Optional.empty();
    }



}
