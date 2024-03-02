package br.com.ekan.api.domain.autenticacao;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(@NotNull @NotEmpty String login, @NotNull @NotEmpty String senha) {
}
