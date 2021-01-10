package io.javabrains.proesof.dtos.conversores;

import io.javabrains.proesof.dtos.DuracaoProjetoResponseDTO;
import io.javabrains.proesof.models.Projeto;

public class ConverterDuracaoParaDTO implements Conversor<DuracaoProjetoResponseDTO,Integer>{
    @Override
    public DuracaoProjetoResponseDTO converter(Integer duracaoProjeto) {
        DuracaoProjetoResponseDTO duracaoProjetoResponseDTO = new DuracaoProjetoResponseDTO();
        Projeto projeto = new Projeto();
        duracaoProjeto = projeto.duracaoProjeto();
        duracaoProjetoResponseDTO.setDuracao(duracaoProjeto);
        return duracaoProjetoResponseDTO;
    }
}
