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
class TarefaRepositoryTest {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EmpregadoRepository empregadoRepository;


    @Test
    public void testeCriacaoTarefa() {
        Projeto projeto = new Projeto();
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();

        tarefa.setDuracaoHoras(5);
        tarefa.setNome("API Tarefa");
        empregado.setCargo(Cargo.DESENVOLVEDOR_JUNIOR);
        tarefa.setProjeto(projeto);
        tarefa.setEmpregado(empregado);
        empregado.adicionaTarefa(tarefa);

        assertEquals(0, projetoRepository.count());

        assertEquals(0, tarefaRepository.count());
        assertEquals(0, empregadoRepository.count());

        assertNull(projeto.getId());
        assertNull(empregado.getId());
        tarefaRepository.save(tarefa);
        assertNotNull(empregado.getId());
        assertNotNull(projeto.getId());

        assertEquals(1, tarefaRepository.count());
        assertEquals(1, empregadoRepository.count());

        assertEquals(1, projetoRepository.count());

    }
}