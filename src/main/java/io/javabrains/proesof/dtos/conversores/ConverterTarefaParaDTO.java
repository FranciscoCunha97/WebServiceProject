package io.javabrains.proesof.dtos.conversores;

import io.javabrains.proesof.dtos.TarefaResponseDTO;
import io.javabrains.proesof.models.Tarefa;

public class ConverterTarefaParaDTO implements Conversor<TarefaResponseDTO, Tarefa>{
    @Override
    public TarefaResponseDTO converter(Tarefa tarefa) {
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO();
        tarefaResponseDTO.setEmpregado(tarefa.getEmpregado());
        tarefaResponseDTO.setProjeto(tarefa.getProjeto());
        tarefaResponseDTO.setNome(tarefa.getNome());
        tarefaResponseDTO.setDuracaoHoras(tarefa.getDuracaoHoras());
        return tarefaResponseDTO;
    }
}
