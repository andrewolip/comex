package br.com.alura.comex.dto;

import java.math.BigDecimal;

public interface PedidoPorCategoriaDto {
    String getCategoriaNome();
    Integer getQuantidadeDeProdutosVendidos();
    BigDecimal getMontanteVendido();
}
