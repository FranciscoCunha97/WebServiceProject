package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.DTOStaticFactory;
import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.dtos.EmpregadoResponseDTO;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.services.EmpregadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    private final EmpregadoService empregadoService;
    private final DTOStaticFactory dtoStaticFactory = DTOStaticFactory.getInstance();

    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }

    @PostMapping
    public ResponseEntity<EmpregadoResponseDTO> createEmpregado(@RequestBody EmpregadoCreateDTO empregado){
        Optional<Empregado> optionalEmpregado = empregadoService.createEmpregado(empregado.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(dtoStaticFactory.empregadoResponseDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
