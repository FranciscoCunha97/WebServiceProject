package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListaProjetoIDUseCase {

    private ProjetoRepository projetoRepository;

    @Autowired
    public ListaProjetoIDUseCase(ProjetoRepository projetoRepository)
    {
        this.projetoRepository = projetoRepository;
    }

    public Optional<Projeto> findById(Long id)
    {
        return projetoRepository.findById(id);
    }
}
