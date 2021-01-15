package io.javabrains.proesof.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class TarefaCreateDTO implements CreateDTO<Tarefa> {

    private String nome;
    private int duracaoHoras;


    @Override
    public Tarefa converter() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDuracaoHoras(duracaoHoras);
        tarefa.setNome(nome);
        return tarefa;
    }
}
