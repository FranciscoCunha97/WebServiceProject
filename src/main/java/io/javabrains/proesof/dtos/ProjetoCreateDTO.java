package io.javabrains.proesof.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.javabrains.proesof.models.Projeto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjetoCreateDTO implements CreateDTO<Projeto>{

   private String nome;
   private LocalDate dataInicio;
   private List<TarefaCreateDTO> tarefas = new ArrayList<>();

    @Override
    public Projeto converter() {
        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setDataInicio(dataInicio);
        projeto.setTarefas(tarefas.stream().map(TarefaCreateDTO::converter).collect(Collectors.toList()));
        return projeto;
    }
}
