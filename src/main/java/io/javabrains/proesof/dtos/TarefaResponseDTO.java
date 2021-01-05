package io.javabrains.proesof.dtos;


import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {
    private String nome;
    private int duracaoHoras;
    private Empregado empregado;
    private Projeto projeto;
}
