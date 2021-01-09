package io.javabrains.proesof.services;

import io.javabrains.proesof.models.*;
import io.javabrains.proesof.repositories.ClienteRepository;
import io.javabrains.proesof.repositories.EmpregadoRepository;
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

@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest
{

    @Autowired
    private EmpregadoService empregadoService;
    @MockBean
    private EmpregadoRepository empregadoRepository;
    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaRepository tarefaRepository;
    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    void findAllEmpregados()
    {
        when(empregadoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAllEmpregados());
    }

    @Test
    void findEmpregadoById ()
    {
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findEmpregadoById(1L).isPresent());
        assertTrue(empregadoService.findEmpregadoById(2L).isEmpty());
    }

    @Test
    void consultarTarefa()
    {
       Tarefa tarefa = new Tarefa();
       Empregado empregado = new Empregado();
       tarefa.setDuracaoHoras(10);
       tarefa.setEmpregado(empregado);

       when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));
       when(tarefaRepository.findAll()).thenReturn(new ArrayList<>());
       assertNotNull(empregadoService.consultarTarefa(1L));
       assertNull(empregadoService.consultarTarefa(2L));
    }

    @Test
    void createEmpregado()
    {
        String mail = "existMail";
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");

        empregado.adicionaTarefa(tarefa);

        when(tarefaRepository.findByNome("testes")).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        assertTrue(empregadoService.createEmpregado(empregado).isPresent());
        when(empregadoRepository.findByEmail("ronaldo@cr.pt")).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.createEmpregado(empregado).isEmpty());
    }

    @Test
    void createTarefa()
    {
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");

        tarefa.setEmpregado(empregado);

        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(empregadoService.createTarefa(tarefa, 1L).isPresent());
        when(tarefaRepository.findByNome("testes")).thenReturn(Optional.of(tarefa));
        assertTrue(empregadoService.createTarefa(tarefa, 1L).isEmpty());
    }

    @Test
    void createProjeto()
    {
        Cliente cliente = new Cliente();
        cliente.setEmail("cliente@gmail.com");
        cliente.setNome("Jorge");
        Projeto projeto = new Projeto();
        projeto.setNome("web");

       projeto.setCliente(cliente);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(projetoRepository.save(projeto)).thenReturn(projeto);
        assertTrue(empregadoService.createProjeto(projeto, 1L).isPresent());
        when(projetoRepository.findByNome("web")).thenReturn(Optional.of(projeto));
        assertTrue(empregadoService.createProjeto(projeto, 1L).isEmpty());

    }

    @Test
    void registaPercentual()
    {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        TarefaPlaneamento tp = new TarefaPlaneamento();
        tp.setPercentualConclusao(80);

        tarefa.setEmpregado(empregado);
        tarefa.setTarefaPlaneamento(tp);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.findByEmail("ronaldo@cr.pt")).thenReturn(Optional.of(empregado));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(empregadoService.registaPercentual(1L, 80).isPresent());
        assertTrue(empregadoService.registaPercentual(2L, 100).isEmpty());
    }

    @Test
    void marcaExecucaoJaRealizadas()
    {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        TarefaPlaneamento tp = new TarefaPlaneamento();

        tarefa.setEmpregado(empregado);
        tarefa.setTarefaPlaneamento(tp);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.findByEmail("ronaldo@cr.pt")).thenReturn(Optional.of(empregado));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        tp.setPercentualConclusao(80);
        assertTrue(empregadoService.marcaExecucaoJaRealizadas(1L).isEmpty());
        tp.setPercentualConclusao(100);
        assertTrue(empregadoService.marcaExecucaoJaRealizadas(1L).isPresent());

    }
}