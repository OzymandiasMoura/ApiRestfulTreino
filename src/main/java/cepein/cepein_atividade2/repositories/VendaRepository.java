package cepein.cepein_atividade2.repositories;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.ids.VendaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>
{
    List<Venda> findByProduto(Produto produto);
    List<Venda> findByPedido(Pedido pedido);
    Optional<Venda> findById(Serializable id);
}
