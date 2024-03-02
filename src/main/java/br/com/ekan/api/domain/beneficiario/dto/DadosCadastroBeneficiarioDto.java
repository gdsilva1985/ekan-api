package br.com.ekan.api.domain.beneficiario.dto;

import br.com.ekan.api.domain.beneficiario.Beneficiario;
import br.com.ekan.api.domain.beneficiario.Documento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosCadastroBeneficiarioDto {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    private String telefone;
    @NotNull
    private LocalDate dataNascimento;
    private List<DadosCadastroDocumentoDto> documentos;

    public List<Documento> converterDocumento() {
        List<Documento> documentosEntidades = new ArrayList<>();
        documentos.stream().forEach(documentoDto -> documentosEntidades.add(documentoDto.converter()));
        return documentosEntidades;
    }

    public Beneficiario converter() {
       return new Beneficiario(nome, telefone, dataNascimento, LocalDateTime.now(), LocalDateTime.now(), converterDocumento());
    }
}

