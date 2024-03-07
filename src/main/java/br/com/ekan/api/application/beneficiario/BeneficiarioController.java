package br.com.ekan.api.application.beneficiario;

import br.com.ekan.api.domain.beneficiario.dto.DadosAtualizacaoBeneficiarioDto;
import br.com.ekan.api.domain.beneficiario.dto.DadosCadastroBeneficiarioDto;
import br.com.ekan.api.domain.beneficiario.Beneficiario;
import br.com.ekan.api.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/beneficiario")
public class BeneficiarioController {
    @Autowired
    private BeneficiarioService service;

    @GetMapping
    public ResponseEntity<Page<Beneficiario>> lista(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.lista(paginacao));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> salva(@RequestBody @Valid DadosCadastroBeneficiarioDto beneficiario, UriComponentsBuilder uriBuider) {
        Beneficiario save = service.salva(beneficiario.converter());
        URI uri = uriBuider.path("/v1/beneficiarios/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoBeneficiarioDto beneficiario) throws NotFoundException {
        return ResponseEntity.ok(service.atualiza(id, beneficiario.converter()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) throws Exception {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/documentos")
    public ResponseEntity<?> listaDocumentos(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.listaDocumentos(id));
    }
}
