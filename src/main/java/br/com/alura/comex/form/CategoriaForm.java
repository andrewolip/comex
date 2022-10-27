package br.com.alura.comex.form;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record CategoriaForm(
        @NotBlank @Min(2) String nome,
        @Nullable StatusCategoria status) {

    public CategoriaForm {
        status = StatusCategoria.ATIVA;
    }

    public Categoria converter() {
        return new Categoria(nome, status);
    }
}
