package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.EmpregadoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoServiceFacade implements EmpregadoService {

    private final CriaEmpregadoUseCase criaEmpregadoUseCase;
    private final GestorTarefaUseCase gestorTarefaUseCase;
    private final GestorProjetoUseCase gestorProjetoUseCase;
    private final RegistaPercentualUseCase registaPercentualUseCase;
    private final MarcaExecucaoRealizadasUseCase marcaExecucaoRealizadasUseCase;
    private final ListaTodosOsEmpregadosUseCase listaTodosOsEmpregadosUseCase;
    private final ListaEmpregadoIDUseCase listaEmpregadoIDUseCase;
    private final EmpregadoConsultaTarefaUseCase empregadoConsultaTarefaUseCase;


    public EmpregadoServiceFacade(CriaEmpregadoUseCase criaEmpregadoUseCase, GestorTarefaUseCase gestorTarefaUseCase, GestorProjetoUseCase gestorProjetoUseCase, RegistaPercentualUseCase registaPercentualUseCase, MarcaExecucaoRealizadasUseCase marcaExecucaoRealizadasUseCase, ListaTodosOsEmpregadosUseCase listaTodosOsEmpregadosUseCase, ListaEmpregadoIDUseCase listaEmpregadoIDUseCase, EmpregadoConsultaTarefaUseCase empregadoConsultaTarefaUseCase) {
        this.criaEmpregadoUseCase = criaEmpregadoUseCase;
        this.gestorTarefaUseCase = gestorTarefaUseCase;
        this.gestorProjetoUseCase = gestorProjetoUseCase;
        this.registaPercentualUseCase = registaPercentualUseCase;
        this.marcaExecucaoRealizadasUseCase = marcaExecucaoRealizadasUseCase;
        this.listaTodosOsEmpregadosUseCase = listaTodosOsEmpregadosUseCase;
        this.listaEmpregadoIDUseCase = listaEmpregadoIDUseCase;
        this.empregadoConsultaTarefaUseCase = empregadoConsultaTarefaUseCase;
    }


    @Override
    public List<Empregado> findAllEmpregados() {
        return listaTodosOsEmpregadosUseCase.findAllEmpregados();
    }

    @Override
    public Optional<Empregado> findById(Long id) {
        return listaEmpregadoIDUseCase.findById(id);
    }

    @Override
    public List<Tarefa> consultarTarefa(Long idEmpregado) {
        return empregadoConsultaTarefaUseCase.consultarTarefa(idEmpregado);
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado) {
        return criaEmpregadoUseCase.createEmpregado(empregado);
    }

    @Override
    public Optional<Empregado> createTarefa(Tarefa tarefa, Long empregadoId) {
        return gestorTarefaUseCase.createTarefa(tarefa,empregadoId);
    }

    @Override
    public Optional<Projeto> createProjeto(Projeto projeto, Long clienteId) {
        return gestorProjetoUseCase.createProjeto(projeto,clienteId);
    }

    @Override
    public Optional<Tarefa> registaPercentual(Long idTarefa, int percentual) {
        return registaPercentualUseCase.registaPercentual(idTarefa,percentual);
    }

    @Override
    public Optional<Tarefa> marcaExecucaoJaRealizadas(Long idTarefa) {
        return marcaExecucaoRealizadasUseCase.marcaExecucaoJaRealizadas(idTarefa);
    }
}
