package cepein.atividade2.repositories;

import cepein.atividade2.domain.Atendente;
import cepein.atividade2.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>
{
    List<Pedido> findAllByDataAberturaPedidoBetween(LocalDate dataInicio, LocalDate dataFim);
    List<Pedido> findByAtendenteOrderByDataAberturaPedido(Atendente atendente);
    List<Pedido> findByAtendenteOrderByDataAberturaPedidoDesc(Atendente atendente);
}
