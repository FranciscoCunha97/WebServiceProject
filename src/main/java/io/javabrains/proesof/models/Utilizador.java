package io.javabrains.proesof.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Data
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class Utilizador
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String nome;
    private String email;
}
