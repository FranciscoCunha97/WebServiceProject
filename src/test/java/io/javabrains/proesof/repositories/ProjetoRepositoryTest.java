package io.javabrains.proesof.repositories;

import io.javabrains.proesof.models.Cargo;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private EmpregadoRepository empregadoRepository;


    @Test
    public void testeCriacaoProjeto() {
        Projeto projeto = new Projeto();
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();

        projeto.setNome("esof");
        projeto.setDataInicio(LocalDate.now());
        tarefa1.setDuracaoHoras(5);
        tarefa2.setDuracaoHoras(10);
        tarefa1.setNome("API Tarefa");
        tarefa2.setNome("tarefa 2");



        assertEquals(0, projeto.getTarefas().size());
        projeto.adicionaTarefaAoProjeto(tarefa1);
        assertEquals(1, projeto.getTarefas().size());


        projeto.adicionaTarefaAoProjeto(tarefa2);
        assertEquals(2, projeto.getTarefas().size());


        assertEquals(0, projetoRepository.count());
        assertEquals(0, tarefaRepository.count());


        assertNull(tarefa1.getId());
        projetoRepository.save(projeto);
        assertNotNull(tarefa1.getId());

        assertEquals(2, tarefaRepository.count());
        assertEquals(1, projetoRepository.count());


    }
}