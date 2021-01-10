package io.javabrains.proesof.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.javabrains.proesof.models.Projeto;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProjetoCreateDTO implements CreateDTO<Projeto>{

   private String nome;
   private LocalDate dataInicio;
   private int valorTotal;
   private int duracao;
   private List<TarefaCreateDTO> tarefas = new ArrayList<>();

    @Override
    public Projeto converter() {
        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setDataInicio(dataInicio);
        projeto.setValorTotal(valorTotal);
        projeto.setTempoTotal(duracao);
        projeto.setTarefas(tarefas.stream().map(TarefaCreateDTO::converter).collect(Collectors.toList()));
        return projeto;
    }
}
