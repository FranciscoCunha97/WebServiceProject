package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.TarefaResponseDTO;
import io.javabrains.proesof.dtos.conversores.ConverterTarefaParaDTO;
import io.javabrains.proesof.services.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;
    private final ConverterTarefaParaDTO converterTarefaParaDTO = new ConverterTarefaParaDTO();

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }


    public ResponseEntity<TarefaResponseDTO> createTarefa()
}
