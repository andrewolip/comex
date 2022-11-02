package br.com.alura.comex.dto;


import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public record CategoriaDto (String nome, StatusCategoria status) {

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream()
                .map(categoria -> new CategoriaDto(categoria.getNome(), categoria.getStatus()))
                .collect(Collectors.toList());
    }
}
