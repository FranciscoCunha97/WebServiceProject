package io.javabrains.proesof.dtos.conversores;

import io.javabrains.proesof.dtos.EmpregadoResponseDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Tarefa;

import java.util.stream.Collectors;

public class ConverterEmpregadoParaDTO implements Conversor <EmpregadoResponseDTO, Empregado>{
    @Override
    public EmpregadoResponseDTO converter(Empregado empregado) {
        EmpregadoResponseDTO empregadoResponseDTO = new EmpregadoResponseDTO();
        empregadoResponseDTO.setEmail(empregado.getEmail());
        empregadoResponseDTO.setCargo(empregado.getCargo());
        empregadoResponseDTO.setTarefas(empregado.getTarefas().stream().map(tarefa -> {
            TarefaCreateDTO tarefaCreateDTO = new TarefaCreateDTO();
            tarefaCreateDTO.setEmpregado(tarefa.getEmpregado());
            tarefaCreateDTO.setDuracaoHoras(tarefa.getDuracaoHoras());
            tarefaCreateDTO.setNome(tarefa.getNome());
            tarefaCreateDTO.setProjeto(tarefa.getProjeto());
            return tarefaCreateDTO;
        }).collect(Collectors.toList()));
        return empregadoResponseDTO;
    }
}
