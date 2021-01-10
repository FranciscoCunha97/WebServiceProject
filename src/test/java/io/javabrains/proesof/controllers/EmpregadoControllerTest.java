package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.services.EmpregadoService;
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

@WebMvcTest(EmpregadoController.class)
class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpregadoService empregadoService;

    private ObjectMapper objectMapper;


    @Test
    void getAllEmpregados() throws Exception{
        Empregado empregado1 = new Empregado();
        Empregado empregado2 = new Empregado();

        List<Empregado> empregados= Arrays.asList(empregado1,empregado2);

        when(empregadoService.findAllEmpregados()).thenReturn(empregados);

        String httpResponseAsString=mockMvc.perform(get("/empregado")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getEmpregadoById() throws Exception{
        Empregado empregado=new Empregado();
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregado);

        when(empregadoService.findById(1L)).thenReturn(Optional.of(empregado));

        String httpResponseAsString=mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound());
    }

    @Test
    void createEmpregado() throws Exception{

        Empregado empregado=new Empregado();
        empregado.setNome("Francisco Cunha");

        when(this.empregadoService.createEmpregado(empregado)).thenReturn(Optional.of(empregado));

        EmpregadoCreateDTO empregadoDTO = new EmpregadoCreateDTO();
        empregadoDTO.setNome("Francisco Cunha");
        String empregadoAsJsonString = new ObjectMapper().writeValueAsString(empregado);

        mockMvc.perform(post("/empregado").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Empregado empregadoExistente = new Empregado();
        empregadoExistente.setNome("Joao Rocha");
        String empregadoExistenteAsJsonString = new ObjectMapper().writeValueAsString(empregadoExistente);

        when(empregadoService.createEmpregado(empregadoExistente)).thenReturn(Optional.empty());
        mockMvc.perform(post("/empregado").content(empregadoExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}