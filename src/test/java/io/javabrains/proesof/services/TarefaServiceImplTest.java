package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = TarefaServiceImpl.class)
class TarefaServiceImplTest {

    @Autowired
    private TarefaService tarefaService;
    @MockBean
    private TarefaRepository tarefaRepository;
    @MockBean
    private EmpregadoRepository empregadoRepository;


    @Test
    void findAll() {
        when(tarefaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaService.findAll());
    }

    @Test
    void findById() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(new Tarefa()));
        assertTrue(tarefaService.findById(1L).isPresent());
        assertTrue(tarefaService.findById(2L).isEmpty());
    }

    @Test
    void createTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        tarefa.setDuracaoHoras(10);
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");

        tarefa.setEmpregado(empregado);

        when(empregadoRepository.findByEmail("ronaldo@cr.pt")).thenReturn(Optional.of(empregado));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(tarefaService.createTarefa(tarefa).isPresent());

        when(tarefaRepository.findByNome("testes")).thenReturn(Optional.of(tarefa));
        assertTrue(tarefaService.createTarefa(tarefa).isEmpty());
    }

    @Test
    void adicionaEmpregadoATarefa() {
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        tarefa.setDuracaoHoras(10);

        empregado.adicionaTarefa(tarefa);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        assertTrue(tarefaService.adicionaEmpregadoATarefa(1L, empregado).isPresent());
        assertFalse(tarefaService.adicionaEmpregadoATarefa(2L, empregado).isPresent());
    }
}