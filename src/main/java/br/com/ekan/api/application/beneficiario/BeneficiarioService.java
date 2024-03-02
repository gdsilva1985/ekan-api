package br.com.ekan.api.application.beneficiario;

import br.com.ekan.api.domain.beneficiario.Beneficiario;
import br.com.ekan.api.domain.beneficiario.BeneficiarioRepository;
import br.com.ekan.api.domain.beneficiario.Documento;
import br.com.ekan.api.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BeneficiarioService {
    @Autowired
    private BeneficiarioRepository repository;

    @Transactional
    public Beneficiario salva(Beneficiario beneficiario) {
        return repository.saveAndFlush(beneficiario);
    }

    public List<Beneficiario> lista() {
        return repository.findAll();
    }

    @Transactional
    public Beneficiario atualiza(Long id, Beneficiario beneficiario) throws NotFoundException {
        Beneficiario byId = getById(id);
        byId.setNome(beneficiario.getNome());
        byId.setTelefone(beneficiario.getTelefone());
        byId.setDataNascimento(beneficiario.getDataNascimento());
        byId.setDataAtualizacao(beneficiario.getDataAtualizacao());
        List<Documento> documentosAtualizacao = getDocumentos(beneficiario, byId);
        byId.getDocumentos().clear();
        byId.getDocumentos().addAll(documentosAtualizacao);
        return salva(byId);
    }

    private List<Documento> getDocumentos(Beneficiario beneficiario, Beneficiario byId) {
        List<Documento> documentosAtualizacao = new ArrayList<>();
        beneficiario.getDocumentos().forEach(documento -> {
            if (byId.getDocumentos().contains(documento)) {
                documentosAtualizacao.add(documento);
            } else {
                if(Objects.isNull(documento.getDataInclusao())) documento.setDataInclusao(LocalDateTime.now());
                documento.setDataAtualizacao(LocalDateTime.now());
                documentosAtualizacao.add(documento);
            }
        });
        return documentosAtualizacao;
    }

    public void remove(Long id) throws Exception {
        repository.delete(getById(id));
    }

    public List<Documento> listaDocumentos(Long id) throws Exception {
        return getById(id).getDocumentos();
    }

    private Beneficiario getById(Long id) throws NotFoundException {
        Optional<Beneficiario> byId = repository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("Beneficiário não encontrado");
        }
        return byId.get();
    }
}
