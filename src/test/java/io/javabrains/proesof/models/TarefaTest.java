package io.javabrains.proesof.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void calcularCusto() {
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();

        tarefa.setDuracaoHoras(5);
        tarefa.setNome("API Tarefa");
        tarefa.setEmpregado(empregado);
        empregado.setCargo(Cargo.DESENVOLVEDOR_JUNIOR);

        int custo = tarefa.calcularCusto();
        assertEquals(50,custo);
    }

    @Test
    void calcularDuracao() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDuracaoHoras(5);

        int duracao = tarefa.calcularDuracao();
        assertEquals(5,duracao);
    }
}