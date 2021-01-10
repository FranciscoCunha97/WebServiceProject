package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListaProjetoTempoUseCase {

    private final ProjetoRepository projetoRepository;


    public ListaProjetoTempoUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Optional<Integer> getDuracaolProjeto(Long idProject) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(idProject);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            //projeto.valorTotal();
            int duracao = projeto.getTempoTotal();
            return Optional.of(duracao);
        }
        return Optional.empty();
    }
}
