package io.javabrains.proesof.dtos;



import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;

import java.util.List;
import java.util.stream.Collectors;

public class DTOStaticFactory {

    private static DTOStaticFactory dtoAbstractFactory;

    private DTOStaticFactory(){
    }

    public static DTOStaticFactory getInstance(){
        if(dtoAbstractFactory == null){
            dtoAbstractFactory = new DTOStaticFactory();
        }
        return dtoAbstractFactory;
    }

    public EmpregadoResponseDTO empregadoResponseDTO(Empregado empregado){
        List<TarefaCreateDTO> createDTOList = empregado.getTarefas().stream().map(tarefa ->
                TarefaCreateDTO.builder()
                .nome(tarefa.getNome())
                .duracaoHoras(tarefa.getDuracaoHoras())
                .empregado(tarefa.getEmpregado())
                .projeto(tarefa.getProjeto()).build()
        ).collect(Collectors.toList());

        /*
        ver se tenho que ter tambem do projeto pois um empregado tambem tem um projeto
         */

        return EmpregadoResponseDTO.builder()
                .email(empregado.getEmail())
                .tarefas(createDTOList)
                .cargo(empregado.getCargo())
                .build();
    }

    public ProjetoResponseDTO projetoResponseDTO (Projeto projeto){
        List<TarefaCreateDTO> createDTOList = projeto.getTarefas().stream().map(tarefa ->
                TarefaCreateDTO.builder()
        .nome(tarefa.getNome())
        .duracaoHoras(tarefa.getDuracaoHoras())
        .empregado(tarefa.getEmpregado())
        .projeto(tarefa.getProjeto()).build()
        ).collect(Collectors.toList());

        return ProjetoResponseDTO.builder()
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .tarefas(createDTOList)
                .build();
    }
}
