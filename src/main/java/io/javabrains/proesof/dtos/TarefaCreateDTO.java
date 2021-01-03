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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaCreateDTO implements CreateDTO<Tarefa> {

    private String nome;
    private int duracaoHoras;
    private Empregado empregado;
    private Projeto projeto;

    @Override
    public Tarefa converter() {
        Tarefa tarefa = new Tarefa();
        tarefa.setEmpregado(empregado);
        tarefa.setDuracaoHoras(duracaoHoras);
        tarefa.setProjeto(projeto);
        tarefa.setNome(nome);
        return tarefa;
    }
}
