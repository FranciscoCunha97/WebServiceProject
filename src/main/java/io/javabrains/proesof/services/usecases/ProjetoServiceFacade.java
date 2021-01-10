package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjetoServiceFacade implements ProjetoService {

    private final CriaProjetoUseCase criaProjetoUseCase;
    private final AdicionaTarefaAoProjetoUseCase adicionaTarefaAoProjetoUseCase;
    private final ListaTodosProjetosUseCase listaTodosProjetosUseCase;
    private final ListaProjetoIDUseCase listaProjetoIDUseCase;
    private final ListaProjetoTempoUseCase listaProjetoTempoUseCase;
    private final ListaProjetoValorUseCase listaProjetoValorUseCase;


    @Autowired
    public ProjetoServiceFacade(CriaProjetoUseCase criaProjetoUseCase, CriaProjetoUseCase criaProjetoUseCase1, AdicionaTarefaAoProjetoUseCase adicionaTarefaAoProjetoUseCase, ListaTodosProjetosUseCase listaTodosProjetosUseCase, ListaProjetoIDUseCase listaProjetoIDUseCase, ListaProjetoTempoUseCase listaProjetoTempoUseCase, ListaProjetoValorUseCase listaProjetoValorUseCase) {
        this.criaProjetoUseCase = criaProjetoUseCase;
        this.adicionaTarefaAoProjetoUseCase = adicionaTarefaAoProjetoUseCase;
        this.listaTodosProjetosUseCase = listaTodosProjetosUseCase;
        this.listaProjetoIDUseCase = listaProjetoIDUseCase;
        this.listaProjetoTempoUseCase = listaProjetoTempoUseCase;
        this.listaProjetoValorUseCase = listaProjetoValorUseCase;
    }

    @Override
    public List<Projeto> findAll() {
        return listaTodosProjetosUseCase.findAll();
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return listaProjetoIDUseCase.findById(id);
    }

    @Override
    public Optional<Projeto> createProjeto(Projeto projeto) {
        return criaProjetoUseCase.createProjeto(projeto);
    }

    @Override
    public Optional<Projeto> createTarefaAoProjeto(Long projetoId, Tarefa tarefa) {
        return adicionaTarefaAoProjetoUseCase.createTarefaAoProjeto(projetoId,tarefa);
    }

    @Override
    public Optional<Integer> getValorTotalProjeto(Long projetoId) {
        return listaProjetoValorUseCase.getValorTotalProjeto(projetoId);
    }

    @Override
    public Optional<Integer> getDuracaoProjeto(Long projetoId) {
        return listaProjetoTempoUseCase.getDuracaolProjeto(projetoId);
    }
}
