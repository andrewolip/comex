package br.com.alura.comex.repositories;

import br.com.alura.comex.dto.PedidoPorCategoriaDto;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT CAT.NOME CategoriaNome," +
                    "       SUM(IPD.QUANTIDADE) QuantidadeDeProdutosVendidos," +
                    "       SUM(IPD.PRECO_UNITARIO) MontanteVendido" +
                    "  FROM categorias CAT," +
                    "     produtos PRO," +
                    "     pedidos PDD," +
                    "     itens_pedido IPD" +
                    " WHERE PRO.ID = IPD.PRODUTO_ID" +
                    "   AND PDD.ID = IPD.PEDIDO_ID" +
                    "   AND CAT.ID = PRO.CATEGORIA_ID " +
                    "GROUP BY CAT.NOME"
    )
    List<PedidoPorCategoriaDto> findPedidosPorCategoria();
}
