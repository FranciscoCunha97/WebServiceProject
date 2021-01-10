package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GestorTarefaUseCase {

    private final EmpregadoRepository empregadoRepository;

    @Autowired
    public GestorTarefaUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }


    public Optional<Empregado> createTarefa(Tarefa tarefa, Long empregadoId) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(empregadoId);
        if (optionalEmpregado.isPresent()) {
            Empregado empregado = optionalEmpregado.get();
            int quantidadeTarefasAntes = empregado.getTarefas().size();
            empregado.adicionaTarefa(tarefa);
            int quantidadeTarefasDepois = empregado.getTarefas().size();
            if (quantidadeTarefasAntes != quantidadeTarefasDepois)
                return Optional.of(empregado);
        }
        return Optional.empty();
    }
}