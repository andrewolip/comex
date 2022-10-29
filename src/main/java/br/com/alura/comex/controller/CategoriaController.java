package br.com.alura.comex.controller;


import br.com.alura.comex.dto.CategoriaDto;
import br.com.alura.comex.dto.PedidoPorCategoriaDto;
import br.com.alura.comex.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repositories.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaForm categoriaForm, UriComponentsBuilder uriComponentsBuilder) {
        Categoria categoria = categoriaForm.converter();
        categoriaRepository.save(categoria);

        URI uri = uriComponentsBuilder.path("categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria.getNome(), categoria.getStatus()));
    }

    @GetMapping
    public List<CategoriaDto> list() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @GetMapping("/pedidos")
    public List<PedidoPorCategoriaDto> listPedidosPorCategoria() {
        return this.categoriaRepository.findPedidosPorCategoria();
    }

}
