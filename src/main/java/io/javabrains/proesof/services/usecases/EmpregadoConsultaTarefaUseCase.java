package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoConsultaTarefaUseCase {

    private final EmpregadoRepository empregadoRepository;


    @Autowired
    public EmpregadoConsultaTarefaUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public List<Tarefa> consultarTarefa(Long idEmpregado)
    {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idEmpregado);
        if (optionalEmpregado.isPresent())
            return optionalEmpregado.get().getTarefas();
        return null;
    }
}
