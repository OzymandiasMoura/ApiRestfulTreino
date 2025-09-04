package cepein.cepein_atividade2.config;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.repositories.PedidoRepository;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import cepein.cepein_atividade2.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig
{
    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Bean
    public List<Atendente> startAtendenteDb()
    {
        Atendente a1 = new Atendente("Pedro", "11111111111", true);
        Atendente a2 = new Atendente("João", "11111111112", true);
        Atendente a3 = new Atendente("Miguel", "11111111113", false);
        Atendente a4 = new Atendente("Flavia", "11111111114", true);

        return atendenteRepository.saveAll(List.of(a1, a2, a3, a4));
    }

    @Bean
    public List<Produto> startProdutosDb()
    {
        Produto p1 = new Produto("Geladeira", "1111111111111", 1260.50, true);
        Produto p2 = new Produto("Televisão", "1111111111112", 1790.75,  true);
        Produto p3 = new Produto("Forno", "1111111111113", 500.00,  true);
        Produto p4 = new Produto("Ventilador", "1111111111114", 120.50, true);
        Produto p5 = new Produto("ArCondicionado", "1111111111115", 3250.00, true);
        Produto p6 = new Produto("Notebook", "1111111111116", 1560.00, false);

        return produtoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));
    }

    @Bean
    public List<Pedido> startPedidosDb()
    {
        Pedido p1 = new Pedido(LocalDate.of(2025, 1, 1), startAtendenteDb().get(0));
        Pedido p2 = new Pedido(LocalDate.of(2025, 1, 12), startAtendenteDb().get(1));
        Pedido p3 = new Pedido(LocalDate.of(2025, 1, 10), startAtendenteDb().get(2));
        Pedido p4 = new Pedido(LocalDate.of(2025, 1, 5), startAtendenteDb().get(3));
        Pedido p5 = new Pedido(LocalDate.of(2025, 1, 6), startAtendenteDb().get(1));

        return pedidoRepository.saveAll(List.of(p1, p2, p3, p4, p5));
    }

    @Bean
    public List<Venda> startVendasDb()
    {
        Venda v1 = new Venda(startPedidosDb().get(0), startProdutosDb().get(0), 3, 0.15);
        Venda v2 = new Venda(startPedidosDb().get(0), startProdutosDb().get(1), 1, 0.15);
        Venda v3 = new Venda(startPedidosDb().get(0), startProdutosDb().get(2), 5, 0.15);
        Venda v4 = new Venda(startPedidosDb().get(1), startProdutosDb().get(5), 1, 0.20);
        Venda v5 = new Venda(startPedidosDb().get(1), startProdutosDb().get(3), 2, 0.10);
        Venda v6 = new Venda(startPedidosDb().get(1), startProdutosDb().get(2), 1, 0.3);
        Venda v7 = new Venda(startPedidosDb().get(2), startProdutosDb().get(5), 1, 0.2);
        Venda v8 = new Venda(startPedidosDb().get(2), startProdutosDb().get(2), 1, 0.2);
        Venda v9 = new Venda(startPedidosDb().get(3), startProdutosDb().get(4), 1, 0.15);
        Venda v10 = new Venda(startPedidosDb().get(4), startProdutosDb().get(2), 2, 0.15);
        Venda v11 = new Venda(startPedidosDb().get(4), startProdutosDb().get(5), 1, 0.2);
        Venda v12 = new Venda(startPedidosDb().get(4), startProdutosDb().get(3), 1, 0.25);

        return vendaRepository.saveAll(List.of(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10,v11, v12));
    }
}
