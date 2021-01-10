package io.javabrains.proesof.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
public class Empregado extends Utilizador
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Cargo cargo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();


    /**
     * Adiciona uma tarefa ao Empregado
     * @param tarefa tarefa a ser adicionada
     */
    public void adicionaTarefa (Tarefa tarefa)
    {
        if (!this.tarefas.contains(tarefa))
        {
            tarefas.add(tarefa);
        }
    }

    /**
     * Atribui um valor ao cargo de cada tipo de empregado
     * @return returna o valor de cada cargo
     */
    public int getValorHora()
    {
        if (cargo==Cargo.ANALISTA_JUNIOR)
            return 20;
        else if (cargo==Cargo.DESENVOLVEDOR_JUNIOR)
            return 10;
        else if (cargo==Cargo.ANALISTA_SENIOR)
            return 80;
        else if(cargo==Cargo.DESENVOLVEDOR_SENIOR)
            return 40;
        return 0;
    }


}
