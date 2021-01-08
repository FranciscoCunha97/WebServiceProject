package io.javabrains.proesof.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@EqualsAndHashCode
public class Projeto
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataInicio;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();


    public void adicionaTarefaAoProjeto (Tarefa tarefa)
    {
        if (!this.tarefas.contains(tarefa))
        {
            tarefas.add(tarefa);
            tarefa.setProjeto(this);
        }
    }

    public int duracaoProjeto()
    {
        int num=0;
        for (Tarefa tafera : this.tarefas)
            num = num + tafera.getDuracaoHoras();
        return horasToDias(num);
    }

    public int horasToDias(int horas)
    {
        int num, dias=1;
        for (num=horas; num>8; num=num-8)
            dias++;
        return dias;
    }
    
    public int valorTotal()
    {
        int num=0;
        for (Tarefa tafera : this.tarefas)
            num = num + tafera.calcularCusto();
        return num;
    }
}
