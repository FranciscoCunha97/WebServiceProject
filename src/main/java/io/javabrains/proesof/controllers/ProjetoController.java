package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.*;
import io.javabrains.proesof.dtos.conversores.ConverterDuracaoParaDTO;
import io.javabrains.proesof.dtos.conversores.ConverterProjetoParaDTO;
import io.javabrains.proesof.dtos.conversores.ConverterValorParaDTO;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.services.ProjetoService;
import io.swagger.models.properties.ObjectProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/projeto")
public class ProjetoController {
    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO = new ConverterProjetoParaDTO();
    private final ConverterValorParaDTO converterValorParaDTO = new ConverterValorParaDTO();
    private final ConverterDuracaoParaDTO converterDuracaoParaDTO = new ConverterDuracaoParaDTO();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> createProjeto(@RequestBody ProjetoCreateDTO projeto){
        Optional<Projeto> optionalProjeto = projetoService.createProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(converterProjetoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{projetoId}")
    public ResponseEntity<ProjetoResponseDTO> adicionaTarefa(@PathVariable Long projetoId, @RequestBody TarefaCreateDTO tarefa){
        Optional<Projeto> optionalProjeto = projetoService.createTarefaAoProjeto(projetoId,tarefa.converter());
        return optionalProjeto.map(projeto -> ResponseEntity.ok(converterProjetoParaDTO.converter(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/projeto/{id}/valor")
    public ResponseEntity<ValorTotalProjetoResponseDTO> getValorTotalProjeto(@PathVariable Long projetoId){
        Optional<Integer> optionalprojeto = projetoService.getValorTotalProjeto(projetoId);
        return optionalprojeto.map(projeto -> {
            ValorTotalProjetoResponseDTO valorProjetoDTO = converterValorParaDTO.converter(projeto);
            return ResponseEntity.ok(valorProjetoDTO);
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tempo")
    public ResponseEntity<DuracaoProjetoResponseDTO> getDuracaoProjeto(@PathVariable Long projetoId){
        Optional<Integer> optionalprojeto = projetoService.getDuracaoProjeto(projetoId);
        return optionalprojeto.map(projeto-> {
            DuracaoProjetoResponseDTO duracaoProjetoDTO = converterDuracaoParaDTO.converter(projeto);
            return ResponseEntity.ok(duracaoProjetoDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
