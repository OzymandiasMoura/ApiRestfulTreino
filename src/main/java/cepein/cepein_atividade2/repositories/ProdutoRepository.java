package cepein.cepein_atividade2.repositories;

import cepein.cepein_atividade2.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{

}
