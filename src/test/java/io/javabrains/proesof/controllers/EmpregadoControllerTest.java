package io.javabrains.proesof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.services.EmpregadoService;
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