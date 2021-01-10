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
    private int valorTotal;
    private int tempoTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();





    /**
     * Adiciona a tarefa ao Projeto
     * @param tarefa tarefa a ser adicionada
     */
    public void adicionaTarefaAoProjeto (Tarefa tarefa)
    {
        if (!this.tarefas.contains(tarefa))
        {
            tarefas.add(tarefa);
            tarefa.setProjeto(this);
        }

    }

    /**
     * Calcula a duracao total do projeto
     * @return o valor da duracao em dias
     */
    public int duracaoProjeto()
    {
        int num=0;
        for (Tarefa tafera : this.tarefas)
            num = num + tafera.getDuracaoHoras();
        return horasToDias(num);
    }

    /**
     * Transforma as horas em dias
     * @param horas a serem enviadas para a transformação
     * @return
     */
    public int horasToDias(int horas)
    {
        int num, dias=1;
        for (num=horas; num>8; num=num-8)
            dias++;
        return dias;
    }

    /**
     * Calcula o valor/Custo total do Projeto
     * @return
     */
    public int valorTotal()
    {
        int num=0;
        for (Tarefa tafera : this.tarefas)
            num = num + tafera.calcularCusto();
        return num;
    }

}
