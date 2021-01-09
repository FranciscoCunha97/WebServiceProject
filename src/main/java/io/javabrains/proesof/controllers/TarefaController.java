package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.dtos.EmpregadoResponseDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.dtos.TarefaResponseDTO;
import io.javabrains.proesof.dtos.conversores.ConverterTarefaParaDTO;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;
    private final ConverterTarefaParaDTO converterTarefaParaDTO = new ConverterTarefaParaDTO();

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }


    @PostMapping
    public ResponseEntity<TarefaResponseDTO> createTarefa(@RequestBody TarefaCreateDTO tarefa){
        Optional<Tarefa> optionalTarefa = tarefaService.createTarefa(tarefa.converter());
        return optionalTarefa.map(value -> ResponseEntity.ok(converterTarefaParaDTO.converter(value))).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{tarefaId}")
    public ResponseEntity<TarefaResponseDTO> adicionaEmpregado (@PathVariable Long tarefaId, @RequestBody EmpregadoCreateDTO empregado){
        Optional<Tarefa> optionalTarefa = tarefaService.adicionaEmpregadoATarefa(tarefaId,empregado.converter());
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
