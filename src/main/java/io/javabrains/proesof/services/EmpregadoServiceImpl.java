package io.javabrains.proesof.services;

import io.javabrains.proesof.models.Cliente;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
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
public class EmpregadoServiceImpl implements EmpregadoService
{

    private final EmpregadoRepository empregadoRepository;
    private TarefaRepository tarefaRepository;
    private ClienteRepository clienteRepository;
    private ProjetoRepository projetoRepository;


    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository, TarefaRepository tarefaRepository, ClienteRepository clienteRepository, ProjetoRepository projetoRepository)
    {
        this.empregadoRepository = empregadoRepository;
        this.tarefaRepository = tarefaRepository;
        this.clienteRepository = clienteRepository;
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<Empregado> findAll()
    {
        List<Empregado> empregados = new ArrayList<>();
        empregadoRepository.findAll().forEach(empregados::add);
        return empregados;
    }

    @Override
    public Optional<Empregado> findById(Long id)
    {
        return empregadoRepository.findById(id);
    }

    @Override
    public List<Tarefa> consultarTarefa(Long idEmpregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idEmpregado);
        if (optionalEmpregado.isPresent())
            return optionalEmpregado.get().getTarefas();
        return null;
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado)
    {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(empregado.getEmail());
        if (optionalEmpregado.isEmpty())
            return Optional.of(empregadoRepository.save(empregado));
        return Optional.empty();
    }

    //VERIFICAR
    @Override
    public Optional<Tarefa> createTarefa(Tarefa tarefa, Long empregadoId)
    {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(empregadoId);
        if (optionalEmpregado.isPresent())
        {
            Empregado empregado = optionalEmpregado.get();
            int quantidadeTarefasAntes = empregado.getTarefas().size();
            empregado.adicionaTarefa(tarefa);
            int quantidadeTarefasDepois = empregado.getTarefas().size();
            if (quantidadeTarefasAntes != quantidadeTarefasDepois)
                return Optional.of(tarefaRepository.save(tarefa));
        }
        return Optional.empty();
    }

    //VERIFICAR
    @Override
    public Optional<Projeto> createProjeto(Projeto projeto, Long clienteId)
    {
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
        if (optionalCliente.isPresent())
        {
            Cliente cliente = optionalCliente.get();
            int quantidadeProjetosAntes = cliente.getProjetos().size();
            cliente.addProjeto(projeto);
            int quantidadeProjetosDepois = cliente.getProjetos().size();
            if (quantidadeProjetosAntes != quantidadeProjetosDepois)
                return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();
    }

}
