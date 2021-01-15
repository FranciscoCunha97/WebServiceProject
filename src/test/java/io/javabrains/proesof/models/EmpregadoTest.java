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
        empregado.setCargo(Cargo.ANALISTA_JUNIOR);

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

    @Test
    void getValorHora(){
        Empregado empregado1 = new Empregado();
        Empregado empregado2 = new Empregado();
        Empregado empregado3 = new Empregado();
        Empregado empregado4 = new Empregado();

        empregado1.setCargo(Cargo.ANALISTA_JUNIOR);
        empregado2.setCargo(Cargo.ANALISTA_SENIOR);
        empregado3.setCargo(Cargo.DESENVOLVEDOR_JUNIOR);
        empregado4.setCargo(Cargo.DESENVOLVEDOR_SENIOR);

        int valor1 = empregado1.getValorHora();
        assertEquals(20,valor1);

        int valor2 = empregado2.getValorHora();
        assertEquals(80,valor2);

        int valor3 = empregado3.getValorHora();
        assertEquals(10,valor3);
        int valor4 = empregado4.getValorHora();
        assertEquals(40,valor4);
    }
}