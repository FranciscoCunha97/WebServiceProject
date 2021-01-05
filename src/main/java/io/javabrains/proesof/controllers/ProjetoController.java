package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.conversores.ConverterProjetoParaDTO;
import io.javabrains.proesof.services.ProjetoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/projeto")
public class ProjetoController {
    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO = new ConverterProjetoParaDTO();


    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }
}
