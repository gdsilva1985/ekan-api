package br.com.ekan.api.domain.beneficiario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EqualsAndHashCode(of = "id")
public class Beneficiario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Documento> documentos;

    public Beneficiario(String nome, String telefone, LocalDate dataNascimento, LocalDateTime dataInclusao,
                        LocalDateTime dataAtualizacao, List<Documento> documentos) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.documentos = setDocumentos(documentos);
    }

    public Beneficiario(Long id, String nome, String telefone, LocalDate dataNascimento, LocalDateTime dataAtualizacao,
                        List<Documento> documentos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataAtualizacao = dataAtualizacao;
        this.documentos = setDocumentos(documentos);
    }

    public List<Documento> setDocumentos(List<Documento> documentos) {
        documentos.stream().forEach(documento -> documento.setBeneficiario(this));
        return documentos;
    }
}
