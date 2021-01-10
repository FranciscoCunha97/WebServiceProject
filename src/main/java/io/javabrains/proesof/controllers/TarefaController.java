package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.*;
import io.javabrains.proesof.dtos.conversores.ConverterTarefaParaDTO;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.models.Tarefa;
import io.javabrains.proesof.services.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;
    private final ConverterTarefaParaDTO converterTarefaParaDTO = new ConverterTarefaParaDTO();

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }


    @GetMapping
    public ResponseEntity<Iterable<TarefaResponseDTO>> getAllTarefas(){
        List<TarefaResponseDTO> tarefaResponseDTOS = new ArrayList<>();
        tarefaService.findAll().forEach(tarefas -> tarefaResponseDTOS.add(converterTarefaParaDTO.converter(tarefas)));
        return ResponseEntity.ok(tarefaResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> getTarefaById(@PathVariable Long id){
        Optional<Tarefa> optionalTarefa=tarefaService.findById(id);
        return optionalTarefa.map(tarefa -> {
            TarefaResponseDTO tarefaResponseDTO=converterTarefaParaDTO.converter(tarefa);
            return ResponseEntity.ok(tarefaResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
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
