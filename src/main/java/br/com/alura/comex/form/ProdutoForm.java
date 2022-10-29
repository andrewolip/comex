package br.com.alura.comex.form;

import br.com.alura.comex.model.Produto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoForm(
        @NotBlank @Min(2) String nome,
        String descricao,
        @NotNull @Min(1) BigDecimal precoUnitario,
        @NotNull Integer quantidadeEmEstoque,
        @NotNull @Min(1) Long categoriaId
) {
    public Produto converter() {
        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque);
    }
}
