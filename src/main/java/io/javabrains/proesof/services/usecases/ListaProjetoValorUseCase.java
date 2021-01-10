package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListaProjetoValorUseCase {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ListaProjetoValorUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Optional<Integer> getValorTotalProjeto(Long idProject) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(idProject);
        if (optionalProjeto.isPresent()) {
            Projeto projeto = optionalProjeto.get();
            //projeto.valorTotal();
            int valorTotal = projeto.valorTotal();
            return Optional.of(valorTotal);
        }
        return Optional.empty();
    }
}
