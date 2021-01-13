package io.javabrains.proesof.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void addProjeto() {
        Projeto projeto = new Projeto();
        Cliente cliente = new Cliente();

        projeto.setNome("Aplicacao Web");
        projeto.setDataInicio(LocalDate.now());

        cliente.setNome("Vasco");
        cliente.setEmail("vasco@gmail.com");


        assertEquals(0,cliente.getProjetos().size());
        cliente.addProjeto(projeto);
        assertEquals(1,cliente.getProjetos().size());
        cliente.addProjeto(projeto);
        assertEquals(1,cliente.getProjetos().size());
    }
}