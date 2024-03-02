package br.com.ekan.api.domain.beneficiario.dto;

import br.com.ekan.api.domain.beneficiario.Documento;
import br.com.ekan.api.domain.beneficiario.TipoDocumento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosAtualizacaoDocumentoDto {
    @NotNull
    private Long id;
    @NotNull
    @NotEmpty
    private TipoDocumento tipoDocumento;
    @NotNull
    @NotEmpty
    private String descricao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
    public Documento converter() {
//        if (Objects.isNull(id)) dataInclusao = LocalDateTime.now();
//        return Documento.builder().id(id).tipoDocumento(tipoDocumento).descricao(descricao)
//                .dataInclusao(dataInclusao).dataAtualizacao(LocalDateTime.now()).build();
        return Documento.builder().id(id).tipoDocumento(tipoDocumento).descricao(descricao)
                .dataInclusao(dataInclusao).dataAtualizacao(dataAtualizacao).build();
    }
}
