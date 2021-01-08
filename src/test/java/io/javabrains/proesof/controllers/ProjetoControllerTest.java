package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.ProjetoCreateDTO;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.services.ProjetoService;
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

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    private final ObjectMapper objectMapper = new ObjectMapper();


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
}