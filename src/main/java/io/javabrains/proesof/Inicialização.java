package io.javabrains.proesof;

import io.javabrains.proesof.models.*;
import io.javabrains.proesof.repositories.ClienteRepository;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.repositories.ProjetoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class Inicialização implements ApplicationListener<ContextRefreshedEvent>
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("\n\n\nInicializou\n\n\n");

        Projeto projetoWeb = new Projeto();
        Cliente vasco = new Cliente();
        Empregado ronaldo = new Empregado();
        Tarefa teste = new Tarefa();

        projetoWeb.setNome("projeto_web");
        projetoWeb.adicionaTarefaAoProjeto(teste);

        teste.setNome("teste");
        teste.setEmpregado(ronaldo);
        teste.setDuracaoHoras(10);

        ronaldo.setCargo(Cargo.ANALISTA_SENIOR);
        ronaldo.adicionaTarefa(teste);
        ronaldo.setNome("Ronaldo");


        this.projetoRepository.save(projetoWeb);
        this.empregadoRepository.save(ronaldo);
        this.clienteRepository.save(vasco);
        this.tarefaRepository.save(teste);

    }
}
