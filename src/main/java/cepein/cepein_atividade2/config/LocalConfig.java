package cepein.cepein_atividade2.config;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
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

    @Bean
    public List<Atendente> startAtendenteDb()
    {
            Atendente a1 = new Atendente("Pedro", "11111111111");
            Atendente a2 = new Atendente("João", "11111111112");
            Atendente a3 = new Atendente("Miguel", "11111111113");
            Atendente a4 = new Atendente("Flavia", "11111111114");

            return atendenteRepository.saveAll(List.of(a1, a2, a3, a4));
    }

}
