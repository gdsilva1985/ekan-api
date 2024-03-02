package br.com.ekan.api.domain.beneficiario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
public class Documento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    private String descricao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JsonBackReference
    private Beneficiario beneficiario;

    public Documento(TipoDocumento tipoDocumento, String descricao, LocalDateTime dataInclusao, LocalDateTime dataAtualizacao) {
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(id, documento.id) && tipoDocumento == documento.tipoDocumento &&
                Objects.equals(descricao, documento.descricao) && Objects.equals(dataInclusao, documento.dataInclusao)
                && Objects.equals(dataAtualizacao, documento.dataAtualizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoDocumento, descricao, dataInclusao, dataAtualizacao, beneficiario);
    }
}
