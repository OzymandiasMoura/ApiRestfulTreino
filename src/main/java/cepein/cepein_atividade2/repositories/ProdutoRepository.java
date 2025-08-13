package cepein.cepein_atividade2.repositories;

import cepein.cepein_atividade2.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
    Optional<Produto> findByBarCode(String barCode);
}
