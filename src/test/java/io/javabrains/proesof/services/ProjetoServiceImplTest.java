package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.ProjetoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
class ProjetoServiceImplTest
{

    @Autowired
    private ProjetoService projetoService;
    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaRepository tarefaRepository;

    @Test
    void findAll()
    {
        when(projetoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById()
    {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertFalse(projetoService.findById(2L).isPresent());
    }

    @Test
    void createTarefaAoProjeto()
    {
        Projeto projeto = new Projeto();
        projeto.setNome("projeto_web");
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNome("testes");
        tarefa1.setProjeto(projeto);
        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNome("services");

        tarefa1.setProjeto(projeto);
        tarefa2.setProjeto(projeto);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(tarefaRepository.save(tarefa1)).thenReturn(tarefa1);
        assertTrue(projetoService.createTarefaAoProjeto(1L, tarefa1).isPresent());
       // when(tarefaRepository.findByNome("testes")).thenReturn(Optional.of(tarefa1));
       // assertTrue(projetoService.createTarefaAoProjeto(1L, tarefa1).isEmpty());
    }
}