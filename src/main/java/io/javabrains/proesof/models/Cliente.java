package io.javabrains.proesof.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Utilizador
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Projeto> projetos = new ArrayList<>();

    public void addProjeto(Projeto projeto){
        if (!this.projetos.contains(projeto))
        {
            projetos.add(projeto);
        }
    }

}
