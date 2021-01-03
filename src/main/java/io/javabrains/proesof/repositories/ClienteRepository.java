package io.javabrains.proesof.repositories;

import io.javabrains.proesof.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository <Cliente, Long>
{

}
