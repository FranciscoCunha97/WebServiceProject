package io.javabrains.proesof.dtos.conversores;

import io.javabrains.proesof.dtos.ProjetoResponseDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.models.Projeto;

import java.util.stream.Collectors;

public class ConverterProjetoParaDTO implements Conversor <ProjetoResponseDTO, Projeto>{
    @Override
    public ProjetoResponseDTO converter(Projeto projeto) {
        ProjetoResponseDTO projetoResponseDTO = new ProjetoResponseDTO();
        projetoResponseDTO.setNome(projeto.getNome());
        projetoResponseDTO.setDataInicio(projeto.getDataInicio());
        projetoResponseDTO.setTarefas(projeto.getTarefas().stream().map(tarefa -> {
            TarefaCreateDTO tarefaCreateDTO = new TarefaCreateDTO();
            tarefaCreateDTO.setProjeto(tarefa.getProjeto());
            tarefaCreateDTO.setEmpregado(tarefa.getEmpregado());
            tarefaCreateDTO.setDuracaoHoras(tarefa.getDuracaoHoras());
            tarefaCreateDTO.setNome(tarefa.getNome());
            return tarefaCreateDTO;
        }).collect(Collectors.toList()));
        return projetoResponseDTO;
    }
}
