package cepein.cepein_atividade2.repositories;

import cepein.cepein_atividade2.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>
{
    Optional<Pedido> findFirstByIdPedidoOrderByDataAberturaPedidoDesc(Integer id);
}
