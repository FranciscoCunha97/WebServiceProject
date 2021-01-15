package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.usecases.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceFacade.class)
public class ProjetoServiceImplTest {

    @MockBean
    private ListaProjetoValorUseCase listaProjetoValorUseCase;

    @MockBean
    private ListaProjetoTempoUseCase listaProjetoTempoUseCase;
    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ListaProjetoIDUseCase listaProjetoIDUseCase;

    @MockBean
    private ListaTodosProjetosUseCase listaTodosProjetosUseCase;

    @MockBean
    private CriaProjetoUseCase criaProjetoUseCase;

    @MockBean
    private AdicionaTarefaAoProjetoUseCase adicionaTarefaAoProjetoUseCase;


    @Test
    void findAll()
    {
        when(listaTodosProjetosUseCase.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById()
    {
        when(listaProjetoIDUseCase.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertFalse(projetoService.findById(2L).isPresent());
    }

    @Test
    void createProjeto(){
        Projeto projeto = new Projeto();
        projeto.setNome("projeto_web");

        when(criaProjetoUseCase.createProjeto(projeto)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.createProjeto(projeto).isPresent());
    }

    @Test
    void createTarefaAoProjeto(){
        Projeto projeto = new Projeto();
        projeto.setNome("projeto_web");
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNome("testes");
        tarefa1.setProjeto(projeto);
        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNome("services");

        tarefa1.setProjeto(projeto);
        tarefa2.setProjeto(projeto);

        when(adicionaTarefaAoProjetoUseCase.createTarefaAoProjeto(1L,tarefa1)).thenReturn(Optional.of(projeto));
        when(adicionaTarefaAoProjetoUseCase.createTarefaAoProjeto(1L,tarefa2)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.createTarefaAoProjeto(1l,tarefa1).isPresent());
        assertTrue(projetoService.createTarefaAoProjeto(1l,tarefa2).isPresent());
    }
}
