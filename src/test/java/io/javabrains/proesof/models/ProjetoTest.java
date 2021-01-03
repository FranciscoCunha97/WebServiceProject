package io.javabrains.proesof.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void adicionaTarefaAoProjeto() {
        Tarefa tarefa = new Tarefa();
        Projeto projeto = new Projeto();
        Empregado empregado = new Empregado();

        projeto.setDataInicio(projeto.getDataInicio());
        projeto.setNome("API WEB REST");

        tarefa.setEmpregado(empregado);
        tarefa.setNome("ServicosTarefa");
        tarefa.setProjeto(projeto);
        tarefa.setDuracaoHoras(3);

        assertEquals(0,projeto.getTarefas().size());
        projeto.adicionaTarefaAoProjeto(tarefa);
        assertEquals(1,projeto.getTarefas().size());
        projeto.adicionaTarefaAoProjeto(tarefa);
    }

    @Test
    void duracaoProjeto() {
        Projeto projeto = new Projeto();
        Empregado empregado = new Empregado();
        Tarefa tarefa = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        Empregado empregado2 = new Empregado();

        projeto.setDataInicio(projeto.getDataInicio());
        projeto.setNome("API WEB REST");

        tarefa.setProjeto(projeto);
        tarefa.setEmpregado(empregado);
        tarefa.setNome("Services Tarefa");
        tarefa.setDuracaoHoras(16);

        tarefa2.setProjeto(projeto);
        tarefa2.setEmpregado(empregado2);
        tarefa2.setNome("Services");
        tarefa2.setDuracaoHoras(10);

        projeto.adicionaTarefaAoProjeto(tarefa);
        projeto.adicionaTarefaAoProjeto(tarefa2);

        int duracaoProjeto = projeto.duracaoProjeto();
        assertEquals(4,duracaoProjeto);
    }

    @Test
    void horasToDias() {
        Projeto projeto = new Projeto();

        int dias = projeto.horasToDias(40);
        assertEquals(5,dias);
    }

    @Test
    void valorTotal() {
        Projeto projeto = new Projeto();
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();

        tarefa.setDuracaoHoras(5);
        tarefa.setEmpregado(empregado);
        projeto.adicionaTarefaAoProjeto(tarefa);
        empregado.setCargo(Cargo.DESENVOLVEDOR_JUNIOR);

        int valor = projeto.valorTotal();
        assertEquals(50,valor);
    }
}