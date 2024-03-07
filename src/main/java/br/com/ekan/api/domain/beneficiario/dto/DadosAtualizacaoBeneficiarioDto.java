package br.com.ekan.api.domain.beneficiario.dto;

import br.com.ekan.api.domain.beneficiario.Beneficiario;
import br.com.ekan.api.domain.beneficiario.Documento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class DadosAtualizacaoBeneficiarioDto {
    @NotBlank
    private Long id;

    @NotBlank
    private String nome;

    @Pattern(regexp = "^\\+?(?:55)?\\(?\\d{2}\\)?[-. ]?(?:\\d{4}|\\d{5})[-. ]?\\d{4}$", message = "O Telefone deve conter o código de area")
    private String telefone;

    @NotNull
    private LocalDate dataNascimento;

    private List<DadosAtualizacaoDocumentoDto> documentos;

    public List<Documento> converterDocumento() {
        List<Documento> documentosEntidades = new ArrayList<>();
        documentos.stream().forEach(documentoDto -> documentosEntidades.add(documentoDto.converter()));
        return documentosEntidades;
    }

    public Beneficiario converter() {
        return new Beneficiario(id, nome, telefone,dataNascimento,LocalDateTime.now(), converterDocumento());
    }
}

