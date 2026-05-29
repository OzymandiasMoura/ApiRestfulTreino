package cepein.atividade2.repositories;

import cepein.atividade2.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
    Optional<Produto> findByBarCode(String barCode);
    List<Produto> findProdutosByPrecoLessThan(Double preco);
    List<Produto> findProdutosByPrecoLessThanEqual(Double preco);
    List<Produto> findProdutosByPrecoGreaterThan(Double preco);
    List<Produto> findProdutosByPrecoGreaterThanEqual(Double preco);
    List<Produto> findProdutosByPrecoIn(List<Double> preco);
}
