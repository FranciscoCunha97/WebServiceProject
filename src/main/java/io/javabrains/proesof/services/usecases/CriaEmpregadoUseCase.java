package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CriaEmpregadoUseCase {


    private final EmpregadoRepository empregadoRepository;

    @Autowired
    public CriaEmpregadoUseCase(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }


    public Optional<Empregado> createEmpregado(Empregado empregado)
    {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(empregado.getEmail());
        if (optionalEmpregado.isEmpty())
            return Optional.of(empregadoRepository.save(empregado));
        return Optional.empty();
    }
}
