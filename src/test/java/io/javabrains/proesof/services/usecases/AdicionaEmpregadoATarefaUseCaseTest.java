package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaServiceFacade.class)
class AdicionaEmpregadoATarefaUseCaseTest {

    @MockBean
    private AdicionaEmpregadoATarefaUseCase adicionaEmpregadoATarefaUseCase;

    @Autowired
    private TarefaService tarefaService;


    @MockBean
    private  CriaTarefaUseCase criaTarefaUseCase;

    @MockBean
    private ListaTodasTarefasUseCase listaTodasTarefasUseCase;

    @MockBean
    private ListaTarefaIDUseCase listaTarefaIDUseCase;




    @Test
    void adicionaEmpregadoATarefa() {
        Empregado empregado = new Empregado();
        empregado.setNome("Ronaldo");
        empregado.setEmail("ronaldo@cr.pt");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("testes");
        tarefa.setDuracaoHoras(10);

        empregado.adicionaTarefa(tarefa);

        when(adicionaEmpregadoATarefaUseCase.adicionaEmpregadoATarefa(1L, empregado)).thenReturn(Optional.of(tarefa));
        assertTrue(tarefaService.adicionaEmpregadoATarefa(1L, empregado).isPresent());

    }
}