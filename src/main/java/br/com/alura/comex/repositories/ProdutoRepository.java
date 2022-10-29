package br.com.alura.comex.repositories;

import br.com.alura.comex.model.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
}
