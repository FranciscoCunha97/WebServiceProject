package io.javabrains.proesof.services;

import io.javabrains.proesof.models.*;
import io.javabrains.proesof.repositories.ClienteRepository;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.repositories.ProjetoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import io.javabrains.proesof.services.usecases.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmpregadoServiceFacade.class)
class EmpregadoServiceImplTest {


    /*
    @MockBean
    private EmpregadoRepository empregadoRepository;
    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaRepository tarefaRepository;
    @MockBean
    private ClienteRepository clienteRepository;
*/


    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
    private CriaEmpregadoUseCase criaEmpregadoUseCase;

    @MockBean
    private GestorTarefaUseCase gestorTarefaUseCase;

    @MockBean
    private GestorProjetoUseCase gestorprojetoUseCase;

    @MockBean
    private RegistaPercentualUseCase registaPercentualUseCase;

    @MockBean
    private MarcaExecucaoRealizadasUseCase marcaExecucaoRealizadasUseCase;

    @MockBean
    private ListaTodosOsEmpregadosUseCase listaTodosOsEmpregadosUseCase;

    @MockBean
    private ListaEmpregadoIDUseCase listaEmpregadoIDUseCase;

    @MockBean
    private EmpregadoConsultaTarefaUseCase empregadoConsultaTarefaUseCase;


    @Test
    void findAllEmpregados()
    {
       when(listaTodosOsEmpregadosUseCase.findAllEmpregados()).thenReturn(new ArrayList<>());
       assertNotNull(empregadoService.findAllEmpregados());
    }

    @Test
    void findById() {
        when(listaEmpregadoIDUseCase.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findById(1L).isPresent());
        assertTrue(empregadoService.findById(2L).isEmpty());
    }

    @Test
    void consultarTarefa(){
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();
        tarefa.setDuracaoHoras(10);
        tarefa.setEmpregado(empregado);

        when(empregadoConsultaTarefaUseCase.consultarTarefa(1L)).thenReturn(empregado.getTarefas());
        assertNotNull(empregado.getTarefas());
    }

    @Test
    public void createEmpregado(){
        String mail = "existMail";
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");

        empregado.adicionaTarefa(tarefa);

        /*
        when(tarefaRepository.findByNome("testes")).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        assertTrue(empregadoService.createEmpregado(empregado).isPresent());
        when(empregadoRepository.findByEmail("ronaldo@cr.pt")).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.createEmpregado(empregado).isEmpty());

         */

        when(criaEmpregadoUseCase.createEmpregado(empregado)).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.createEmpregado(empregado).isPresent());

    }

    @Test
    public void createTarefa(){
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");

        tarefa.setEmpregado(empregado);

        when(gestorTarefaUseCase.createTarefa(tarefa,1L)).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.createTarefa(tarefa,1L).isPresent());
        assertTrue(empregadoService.createTarefa(tarefa,2L).isEmpty());

    }
    @Test
    public void createProjeto(){
        Cliente cliente = new Cliente();
        cliente.setEmail("cliente@gmail.com");
        cliente.setNome("Jorge");
        Projeto projeto = new Projeto();
        projeto.setNome("web");

        projeto.setCliente(cliente);

        when(gestorprojetoUseCase.createProjeto(projeto,1L)).thenReturn(Optional.of(projeto));
        assertTrue(empregadoService.createProjeto(projeto,1L).isPresent());
        assertTrue(empregadoService.createProjeto(projeto,2L).isEmpty());
    }

    @Test
    public void registaPercentual(){
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        TarefaPlaneamento tp = new TarefaPlaneamento();
        tp.setPercentualConclusao(80);

        tarefa.setEmpregado(empregado);
        tarefa.setTarefaPlaneamento(tp);

        when(registaPercentualUseCase.registaPercentual(1L,80)).thenReturn(Optional.of(tarefa));
        assertTrue(empregadoService.registaPercentual(1L, 80).isPresent());
        assertTrue(empregadoService.registaPercentual(2L, 100).isEmpty());
    }

    @Test
    public void marcaExecucaoJaRealizadas(){
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        TarefaPlaneamento tp = new TarefaPlaneamento();

        tarefa.setEmpregado(empregado);
        tarefa.setTarefaPlaneamento(tp);

        when(marcaExecucaoRealizadasUseCase.marcaExecucaoJaRealizadas(1L)).thenReturn(Optional.of(tarefa));
        assertTrue(empregadoService.marcaExecucaoJaRealizadas(1L).isPresent());
        assertTrue(empregadoService.marcaExecucaoJaRealizadas(2L).isEmpty());
    }

}