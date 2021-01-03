package io.javabrains.proesof.models;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;


@Data
@Entity
@EqualsAndHashCode
public class Tarefa
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int duracaoHoras;
    @ManyToOne
    private Empregado empregado;
    @ManyToOne
    private Projeto projeto;



    public int calcularCusto()
    {
        int num = getEmpregado().getValorHora();
        return (num * getDuracaoHoras());
    }

    public int calcularDuracao()
    {
        return duracaoHoras;
    }

}
