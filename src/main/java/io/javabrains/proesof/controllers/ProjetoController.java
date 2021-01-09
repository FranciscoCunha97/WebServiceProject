package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.EmpregadoResponseDTO;
import io.javabrains.proesof.dtos.ProjetoCreateDTO;
import io.javabrains.proesof.dtos.ProjetoResponseDTO;
import io.javabrains.proesof.dtos.TarefaCreateDTO;
import io.javabrains.proesof.dtos.conversores.ConverterProjetoParaDTO;
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

    @GetMapping("/{idProjeto}/valor")
    public ResponseEntity<ProjetoResponseDTO> getValorByProjetoId(@PathVariable Long projetoId){
        Optional<Projeto> optionalProjeto = projetoService.findById(projetoId);
        //Projeto projeto = new Projeto();
        //int valorTotal = projeto.valorTotal();
        return optionalProjeto.map(projeto -> {
            ProjetoResponseDTO projetoResponseDTO = converterProjetoParaDTO.converter(projet);
            return ResponseEntity.ok(projetoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
