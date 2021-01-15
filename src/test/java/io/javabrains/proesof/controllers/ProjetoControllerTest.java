package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.ProjetoCreateDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.services.ProjetoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAllProjetos() throws Exception{
        Projeto projeto1 = new Projeto();
        Projeto projeto2 = new Projeto();

        List<Projeto> projetos= Arrays.asList(projeto1,projeto2);

        when(projetoService.findAll()).thenReturn(projetos);

        String httpResponseAsString=mockMvc.perform(get("/projeto")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getProjetoById() throws Exception{
        Projeto projeto=new Projeto();
        String projetpAsJsonString=new ObjectMapper().writeValueAsString(projeto);

        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));

        String httpResponseAsString=mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }


    @Test
    void createProjeto() throws Exception{
        Projeto projeto = new Projeto();
        projeto.setNome("WebService");

        when(this.projetoService.createProjeto(projeto)).thenReturn(Optional.of(projeto));

        ProjetoCreateDTO projetoDTO = new ProjetoCreateDTO();
        projetoDTO.setNome("WebService");
        String projetoAsJsonString = objectMapper.writeValueAsString(projeto);

        mockMvc.perform(post("/projeto").content(projetoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        Projeto projetoExistente = new Projeto();
        projetoExistente.setNome("WebService");
    }

    @Test
    public void adicionaTarefa() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setNome("WebService");

        TarefaCreateDTO tarefa = new TarefaCreateDTO();

        tarefa.setNome(tarefa.getNome());
        tarefa.setDuracaoHoras(tarefa.getDuracaoHoras());

        String tarefaJson = objectMapper.writeValueAsString(tarefa);

        when(projetoService.createTarefaAoProjeto(1L, tarefa.converter())).thenReturn(Optional.of(projeto));

        mockMvc.perform(
                patch("/projeto/1")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/projeto/2")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void getValorTotalProjeto() throws Exception{
        Projeto projeto = new Projeto();

        when(projetoService.getValorTotalProjeto(1L)).thenReturn(Optional.of(projeto.getValorTotal()));
        String httpResponseAsString = mockMvc.perform(get("/projeto/1/valor")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/projeto/2/valor")).andExpect(status().isNotFound());
    }

    @Test
    public void getDuracaoProjeto() throws Exception{
        Projeto projeto = new Projeto();

        when(projetoService.getDuracaoProjeto(1L)).thenReturn(Optional.of(projeto.getTempoTotal()));
        String httpResponseAsString = mockMvc.perform(get("/projeto/1/tempo")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
        mockMvc.perform(get("/projeto/2/tempo")).andExpect(status().isNotFound());
    }

}