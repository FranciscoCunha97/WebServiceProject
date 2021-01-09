package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void createTarefa() throws Exception{
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Tarefa Testes");

        when(this.tarefaService.createTarefa(tarefa)).thenReturn(Optional.of(tarefa));

        TarefaCreateDTO tarefaDTO = new TarefaCreateDTO();
        tarefaDTO.setNome("Tarefa Testes");
        String tarefaAsJsonString = new ObjectMapper().writeValueAsString(tarefa);

        mockMvc.perform(post("/tarefa").content(tarefaAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Tarefa tarefaExistente = new Tarefa();
        tarefaExistente.setNome("Tarefa Testes");
        String tarefaExistenteAsJsonString = new ObjectMapper().writeValueAsString(tarefaExistente);

        when(tarefaService.createTarefa(tarefaExistente)).thenReturn(Optional.empty());
        mockMvc.perform(post("/tarefa").content(tarefaExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void adicionaEmpregado() throws Exception{
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Testes Controllers");

        EmpregadoCreateDTO empregado = new EmpregadoCreateDTO();

        empregado.setNome(empregado.getNome());
        empregado.setEmail(empregado.getEmail());
        empregado.setCargo(empregado.getCargo());

        String empregadoJson = objectMapper.writeValueAsString(empregado);

        when(tarefaService.adicionaEmpregadoATarefa(1L,empregado.converter())).thenReturn(Optional.of(tarefa));

        mockMvc.perform(
                patch("/tarefa/1")
                        .content(empregadoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/tarefa/2")
                        .content(empregadoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}