package io.javabrains.proesof.repositories;

import io.javabrains.proesof.models.Empregado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface EmpregadoRepository extends CrudRepository<Empregado, Long>
{
    @Query("SELECT e FROM Empregado e join e.tarefas t  where (:cargo is null or e.cargo=:cargo)")
    List<Empregado> pesquisaEmpregados(@Param("tarefa") Tarefa tarefa, @Param("cargo")Cargo cargo);


    Optional<Empregado> findByEmail(String email);

}
