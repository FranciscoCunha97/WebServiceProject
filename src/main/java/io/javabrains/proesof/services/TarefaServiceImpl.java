package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.repositories.ClienteRepository;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.repositories.ProjetoRepository;
import io.javabrains.proesof.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService{

    private TarefaRepository tarefaRepository;
    private EmpregadoRepository empregadoRepository;

    @Autowired
    public TarefaServiceImpl(TarefaRepository tarefaRepository)
    {
        this.tarefaRepository = tarefaRepository;
    }


    @Override
    public List<Tarefa> findAll()
    {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefaRepository.findAll().forEach(tarefas::add);
        return tarefas;
    }

    @Override
    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    @Override
    public Optional<Tarefa> createTarefa(Tarefa tarefa)
    {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findByNome(tarefa.getNome());
        if(optionalTarefa.isEmpty())
            return Optional.of(tarefaRepository.save(tarefa));
        return Optional.empty();
    }

    @Override
    public Optional<Tarefa> adicionaEmpregadoATarefa(Long tarefaId, Empregado empregado)
    {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(tarefaId);
        if(optionalTarefa.isPresent())
        {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setEmpregado(empregado);
            return Optional.of(tarefa);
        }
        return Optional.empty();


    }
}
