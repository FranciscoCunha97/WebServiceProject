package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService
{

    private ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository)
    {
        this.projetoRepository = projetoRepository;
    }


    @Override
    public List<Projeto> findAll()
    {
        List<Projeto> projetos = new ArrayList<>();
        projetoRepository.findAll().forEach(projetos::add);
        return projetos;
    }

    @Override
    public Optional<Projeto> findById(Long id)
    {
        return projetoRepository.findById(id);
    }

    @Override
    public Optional<Projeto> createTarefaAoProjeto(Projeto projeto, Long projetoId)
    {
        Optional<Projeto> optionalProjeto = projetoRepository.findByNome(projeto.getNome());
        if (optionalProjeto.isEmpty())
            return Optional.of(projetoRepository.save(projeto));
        return Optional.empty();
    }
}
