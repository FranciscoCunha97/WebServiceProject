package io.javabrains.proesof.repositories;

import io.javabrains.proesof.models.Cargo;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpregadoRepositoryTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private EmpregadoRepository empregadoRepository;



    @Test
    public void testeCriacaoEmpregado(){
        Projeto projeto = new Projeto();
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        Empregado empregado = new Empregado();
        tarefa1.setDuracaoHoras(5);
        tarefa2.setDuracaoHoras(10);
        tarefa1.setNome("API Tarefa");
        tarefa2.setNome("tarefa 2");
        empregado.setCargo(Cargo.DESENVOLVEDOR_JUNIOR);




        empregado.adicionaTarefa(tarefa1);
        empregado.adicionaTarefa(tarefa2);
        assertEquals(2, empregado.getTarefas().size());


        assertEquals(0, empregadoRepository.count());
        assertEquals(0, tarefaRepository.count());


        assertNull(tarefa1.getId());
        assertNull(projeto.getId());
        empregadoRepository.save(empregado);
        assertNotNull(projeto.getId());
        assertNotNull(tarefa1.getId());

        assertEquals(2, tarefaRepository.count());
        assertEquals(1, empregadoRepository.count());

    }
}