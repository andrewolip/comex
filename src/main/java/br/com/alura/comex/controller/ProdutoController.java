package br.com.alura.comex.controller;

import br.com.alura.comex.dto.ProdutoDto;
import br.com.alura.comex.form.ProdutoForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repositories.CategoriaRepository;
import br.com.alura.comex.repositories.ProdutoRepository;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> save(@RequestBody ProdutoForm produtoForm, UriComponentsBuilder uriComponentsBuilder) {
        Categoria categoria = categoriaRepository.findById(produtoForm.categoriaId())
                .orElseThrow(IllegalArgumentException::new);

        Produto produto = produtoForm.converter();

        produto.setCategoria(categoria);
        produtoRepository.save(produto);

        URI uri = uriComponentsBuilder.path("categorias/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto.converter());
    }

    @GetMapping
    public List<ProdutoDto> list(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable sortedByName = PageRequest.of(page, 5, Sort.by("nome"));
        return produtoRepository.findAll(sortedByName).stream()
                .map(Produto::converter)
                .collect(Collectors.toList());
    }
}
