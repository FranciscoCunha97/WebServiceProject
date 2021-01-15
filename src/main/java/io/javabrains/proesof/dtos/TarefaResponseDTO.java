package io.javabrains.proesof.dtos;


import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaResponseDTO {
    private String nome;
    private int duracaoHoras;
    private Empregado empregado;
    private Projeto projeto;
}
