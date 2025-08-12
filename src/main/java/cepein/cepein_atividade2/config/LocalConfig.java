package cepein.cepein_atividade2.config;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig
{
    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

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
        Produto p1 = new Produto("Geladeira", "1111111111111", 1260.50);
        Produto p2 = new Produto("Televisão", "1111111111112", 1790.75);
        Produto p3 = new Produto("Forno", "1111111111113", 500.00);
        Produto p4 = new Produto("Ventilador", "1111111111114", 120.50);
        Produto p5 = new Produto("ArCondicionado", "1111111111115", 3250.00);
        Produto p6 = new Produto("Notebook", "1111111111116", 1560.00);

        return produtoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));
    }
}
