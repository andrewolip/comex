package br.com.alura.comex.dto;

import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProdutoDto (
        String nome,
        String descricao,
        BigDecimal precoUnitario,
        Integer quantidadeEmEstoque,
        Long categoriaId,
        String categoriaNome
){
    public List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> new ProdutoDto(produto.getNome(), produto.getDescricao(), produto.getPrecoUnitario(), produto.getQuantidadeEstoque(), produto.getCategoria().getId(), produto.getCategoria().getNome()))
                .collect(Collectors.toList());
    }
}
