package io.javabrains.proesof.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void addProjeto()
    {
        Cliente cliente = new Cliente();
        Projeto projeto = new Projeto();
        projeto.setNome("Aplicacao Web");
        projeto.setDataInicio(LocalDate.now());

        assertEquals(0,cliente.getProjetos().size());
        cliente.addProjeto(projeto);
        assertEquals(1, cliente.getProjetos().size());
        cliente.addProjeto(projeto);
        assertEquals(1, cliente.getProjetos().size());
    }
}