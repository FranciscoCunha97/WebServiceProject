package io.javabrains.proesof.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmpregadoTest {

    @Test
    void adicionaTarefa() {
        Projeto projeto = new Projeto();
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();

        projeto.setNome("Aplicacao Web");
        projeto.setDataInicio(LocalDate.now());

        tarefa.setNome("Testes");
        tarefa.setEmpregado(empregado);
        tarefa.setProjeto(projeto);
        tarefa.setDuracaoHoras(5);

        assertEquals(0,empregado.getTarefas().size());
        empregado.adicionaTarefa(tarefa);
        assertEquals(1,empregado.getTarefas().size());
        empregado.adicionaTarefa(tarefa);
        assertEquals(1,empregado.getTarefas().size());
    }
}