package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaTodosProjetosUseCase {
    private ProjetoRepository projetoRepository;

    @Autowired
    public ListaTodosProjetosUseCase(ProjetoRepository projetoRepository)
    {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> findAll()
    {
        List<Projeto> projetos = new ArrayList<>();
        projetoRepository.findAll().forEach(projetos::add);
        return projetos;
    }
}
