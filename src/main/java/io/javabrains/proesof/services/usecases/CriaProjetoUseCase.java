package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CriaProjetoUseCase {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public CriaProjetoUseCase(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

   public Optional<Projeto> createProjeto(Projeto projeto){
        Optional<Projeto> optionalProjeto = projetoRepository.findByNome(projeto.getNome());
        if(optionalProjeto.isEmpty()){
            return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();
   }

}
