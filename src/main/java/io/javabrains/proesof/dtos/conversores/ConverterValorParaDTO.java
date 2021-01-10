package io.javabrains.proesof.dtos.conversores;

import io.javabrains.proesof.dtos.ValorTotalProjetoResponseDTO;
import io.javabrains.proesof.models.Projeto;

public class ConverterValorParaDTO implements Conversor<ValorTotalProjetoResponseDTO, Integer>{

    @Override
    public ValorTotalProjetoResponseDTO converter(Integer custoProjeto) {
        ValorTotalProjetoResponseDTO valorTotalProjetoResponseDTO = new ValorTotalProjetoResponseDTO();
        Projeto projeto = new Projeto();
        custoProjeto = projeto.valorTotal();
        valorTotalProjetoResponseDTO.setValorTotal(custoProjeto);
        return null;
    }
}
