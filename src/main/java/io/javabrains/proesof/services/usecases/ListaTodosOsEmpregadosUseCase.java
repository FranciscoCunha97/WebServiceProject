package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaTodosOsEmpregadosUseCase {

    private final EmpregadoRepository empregadoRepository;

    public ListaTodosOsEmpregadosUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public List<Empregado> findAllEmpregados()
    {
        List<Empregado> empregados = new ArrayList<>();
        empregadoRepository.findAll().forEach(empregados::add);
        return empregados;
    }
}
