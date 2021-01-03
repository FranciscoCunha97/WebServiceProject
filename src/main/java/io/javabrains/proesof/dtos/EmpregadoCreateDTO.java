package io.javabrains.proesof.dtos;


import io.javabrains.proesof.models.Cargo;
import io.javabrains.proesof.models.Empregado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadoCreateDTO implements CreateDTO<Empregado>{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String email;
    private Cargo cargo;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();


    @Override
    public Empregado converter() {
        logger.info("Convertido para Modelo");
        Empregado empregado = new Empregado();
        empregado.setEmail(this.getEmail());
        empregado.setTarefas(tarefas.stream().map(TarefaCreateDTO::converter).collect(Collectors.toList()));
        return empregado;
    }
}
