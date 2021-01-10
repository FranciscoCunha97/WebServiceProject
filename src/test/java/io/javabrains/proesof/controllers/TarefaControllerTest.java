package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAllTarefas() throws Exception{
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();

        List<Tarefa> tarefas= Arrays.asList(tarefa1,tarefa2);

        when(tarefaService.findAll()).thenReturn(tarefas);

        String httpResponseAsString=mockMvc.perform(get("/tarefa")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getTarefaById() throws Exception{
        Tarefa tarefa=new Tarefa();
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(tarefa);

        when(tarefaService.findById(1L)).thenReturn(Optional.of(tarefa));

        String httpResponseAsString=mockMvc.perform(get("/tarefa/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/tarefa/2")).andExpect(status().isNotFound());
    }

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