package io.javabrains.proesof.controllers;


import io.javabrains.proesof.dtos.EmpregadoCreateDTO;
import io.javabrains.proesof.dtos.EmpregadoResponseDTO;
import io.javabrains.proesof.dtos.ProjetoResponseDTO;
import io.javabrains.proesof.dtos.conversores.ConverterEmpregadoParaDTO;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.services.EmpregadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    private final EmpregadoService empregadoService;
    private final ConverterEmpregadoParaDTO converterEmpregadoParaDTO = new ConverterEmpregadoParaDTO();


    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<EmpregadoResponseDTO>> getAllEmpregados(){
        List<EmpregadoResponseDTO> empregadoResponseDTOS = new ArrayList<>();
        empregadoService.findAllEmpregados().forEach(empregado -> empregadoResponseDTOS.add(converterEmpregadoParaDTO.converter(empregado)));
        return ResponseEntity.ok(empregadoResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpregadoResponseDTO> getEmpregadoById(@PathVariable Long id){
        Optional<Empregado> optionalEmpregado=empregadoService.findById(id);
        return optionalEmpregado.map(empregado -> {
            EmpregadoResponseDTO empregadoResponseDTO=converterEmpregadoParaDTO.converter(empregado);
            return ResponseEntity.ok(empregadoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<EmpregadoResponseDTO> createEmpregado(@RequestBody EmpregadoCreateDTO empregado){
        Optional<Empregado> optionalEmpregado = empregadoService.createEmpregado(empregado.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(converterEmpregadoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
