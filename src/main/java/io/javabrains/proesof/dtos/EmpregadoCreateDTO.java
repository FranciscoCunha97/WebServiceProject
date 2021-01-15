package io.javabrains.proesof.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
public class EmpregadoCreateDTO implements CreateDTO<Empregado>{

    @JsonIgnore
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String nome;
    private String email;
    private Cargo cargo;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();


    @Override
    public Empregado converter() {
        logger.info("Convertido para Modelo");
        Empregado empregado = new Empregado();
        empregado.setNome(this.getNome());
        empregado.setEmail(this.getEmail());
        empregado.setTarefas(tarefas.stream().map(TarefaCreateDTO::converter).collect(Collectors.toList()));
        return empregado;
    }
}
