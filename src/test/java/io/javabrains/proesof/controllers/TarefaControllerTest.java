package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    private ObjectMapper objectMapper;


    @Test
    void createTarefa() throws Exception{
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Tarefa Testes");

        when(this.tarefaService.createTarefa(tarefa)).thenReturn(Optional.of(tarefa));

        TarefaCreateDTO tarefaDTO = new TarefaCreateDTO();
        tarefaDTO.setNome("Tarefa Testes");
        String tarefaAsJsonString = objectMapper.writeValueAsString(tarefa);



    }
}