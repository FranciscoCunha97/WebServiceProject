package io.javabrains.proesof.services;


import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService
{

    private ProjetoRepository projetoRepository;

  @Autowired
  public ClienteServiceImpl(ProjetoRepository projetoRepository)
    {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<Projeto> findAllProjects()
    {
        List<Projeto> projetos = new ArrayList<>();
        projetoRepository.findAll().forEach(projetos::add);
        return projetos;
    }

    @Override
    public Optional<Projeto> findProjetoById(Long projetoId)
    {
        return projetoRepository.findById(projetoId);
    }

    @Override
    public Optional<Projeto> findProjetoByNome(String projetoNome)
    {
        return projetoRepository.findByNome(projetoNome);
    }
}
