package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Projeto;
import lombok.Data;

import java.util.List;
import java.util.Optional;

public interface ClienteService
{
    public List<Projeto> findAllProjects();
    Optional<Projeto> findProjetoById(Long projetoId);
    Optional<Projeto> findProjetoByNome(String projetoNome);
}
