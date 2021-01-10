package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.ProjetoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdicionaTarefaAoProjetoUseCase {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public AdicionaTarefaAoProjetoUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Optional<Projeto> createTarefaAoProjeto(Long projetoId, Tarefa tarefa)
    {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoId);
        if (optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            projeto.adicionaTarefaAoProjeto(tarefa);
            return Optional.of(projeto);
        }
        return Optional.empty();
    }
}
