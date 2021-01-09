package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ClienteRepository;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ClienteServiceImpl.class)
class ClienteServiceImplTest {


    @Autowired
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ProjetoRepository projetoRepository;

    @Test
    void findAllProjects() {
        when(projetoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteService.findAllProjects());
    }

    @Test
    void findProjetoById() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(clienteService.findProjetoById(1L).isPresent());
        assertTrue(clienteService.findProjetoById(2L).isEmpty());
    }

    @Test
    void findProjetoByNome() {
        when(projetoRepository.findByNome("nome")).thenReturn(Optional.of(new Projeto()));
        assertTrue(clienteService.findProjetoByNome("nome").isPresent());
        assertTrue(clienteService.findProjetoByNome("errado").isEmpty());
    }
}