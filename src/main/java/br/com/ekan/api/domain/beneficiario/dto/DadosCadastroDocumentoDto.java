package br.com.ekan.api.domain.beneficiario.dto;

import br.com.ekan.api.domain.beneficiario.Documento;
import br.com.ekan.api.domain.beneficiario.TipoDocumento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosCadastroDocumentoDto {
    @NotNull
    @NotEmpty
    private TipoDocumento tipoDocumento;
    @NotNull
    @NotEmpty
    private String descricao;

    public Documento converter() {
        return Documento.builder().tipoDocumento(tipoDocumento).descricao(descricao)
                .dataInclusao(LocalDateTime.now()).dataAtualizacao(LocalDateTime.now()).build();
    }
}
