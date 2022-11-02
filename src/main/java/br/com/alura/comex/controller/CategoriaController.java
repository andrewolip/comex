package br.com.alura.comex.controller;


import br.com.alura.comex.dto.CategoriaDto;
import br.com.alura.comex.dto.PedidoPorCategoriaDto;
import br.com.alura.comex.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repositories.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Categorias")
@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Operation(summary = "${categoria.save}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) })
    })
    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaForm categoriaForm, UriComponentsBuilder uriComponentsBuilder) {
        Categoria categoria = categoriaForm.converter();
        categoriaRepository.save(categoria);

        URI uri = uriComponentsBuilder.path("categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria.getNome(), categoria.getStatus()));
    }

    @Operation(summary = "${categoria.list}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) })
    })
    @GetMapping
    public List<CategoriaDto> list() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @Operation(summary = "${categoria.listPedidosPorCategoria}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) })
    })
    @GetMapping("/pedidos")
    public List<PedidoPorCategoriaDto> listPedidosPorCategoria() {
        return this.categoriaRepository.findPedidosPorCategoria();
    }

}
