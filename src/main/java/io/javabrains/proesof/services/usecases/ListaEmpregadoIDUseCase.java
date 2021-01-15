package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.services.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListaEmpregadoIDUseCase{

    private final EmpregadoRepository empregadoRepository;

    @Autowired
    public ListaEmpregadoIDUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    public Optional<Empregado> findById(Long id)
    {
        return empregadoRepository.findById(id);
    }

}
