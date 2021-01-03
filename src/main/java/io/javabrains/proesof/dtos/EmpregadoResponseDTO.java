package io.javabrains.proesof.dtos;

import io.javabrains.proesof.models.Cargo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoResponseDTO{
  private String email;
  private Cargo cargo;
  private List<TarefaCreateDTO> tarefas = new ArrayList<>();

}
