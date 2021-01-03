package io.javabrains.proesof.repositories;

import io.javabrains.proesof.models.Projeto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProjetoRepository extends CrudRepository <Projeto, Long>
{

    Optional<Projeto> findByNome(String nome);
}
