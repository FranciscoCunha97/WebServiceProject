package io.javabrains.proesof.repositories;

import io.javabrains.proesof.models.Cliente;
import io.javabrains.proesof.models.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testeCriacaoCliente(){

        Cliente cliente = new Cliente();
        Projeto projeto1 = new Projeto();
        Projeto projeto2 = new Projeto();

        projeto1.setNome("esof");
        projeto1.setDataInicio(LocalDate.now());
        projeto2.setNome("proj");
        projeto2.setDataInicio(LocalDate.now());

        cliente.addProjeto(projeto1);
        cliente.addProjeto(projeto2);
        assertEquals(2, cliente.getProjetos().size());

        assertEquals(0, projetoRepository.count());
        assertEquals(0, clienteRepository.count());

        assertNull(projeto1.getId());
        assertNull(projeto2.getId());
        assertNull(cliente.getId());

        clienteRepository.save(cliente);

        assertNotNull(projeto1.getId());
        assertNotNull(projeto2.getId());
        assertNotNull(cliente.getId());

        assertEquals(2, projetoRepository.count());
        assertEquals(1, clienteRepository.count());
    }
}