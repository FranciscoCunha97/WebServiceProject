package io.javabrains.proesof.services.usecases;

import io.javabrains.proesof.models.Cliente;
import io.javabrains.proesof.models.Empregado;
import io.javabrains.proesof.models.Projeto;
import io.javabrains.proesof.repositories.ClienteRepository;
import io.javabrains.proesof.repositories.EmpregadoRepository;
import io.javabrains.proesof.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GestorProjetoUseCase {
    private final EmpregadoRepository empregadoRepository;
    private final ProjetoRepository projetoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public GestorProjetoUseCase(EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository, ClienteRepository clienteRepository) {
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Optional<Projeto> createProjeto(Projeto projeto, Long clienteId)
    {
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
        if (optionalCliente.isPresent())
        {
            Cliente cliente = optionalCliente.get();
            int quantidadeProjetosAntes = cliente.getProjetos().size();
            cliente.addProjeto(projeto);
            int quantidadeProjetosDepois = cliente.getProjetos().size();
            if (quantidadeProjetosAntes != quantidadeProjetosDepois)
                return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();
    }
}
