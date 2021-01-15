package io.javabrains.proesof.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjetoResponseDTO {
    private String nome;
    private LocalDate dataInicio;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();


}
