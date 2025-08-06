package cepein.cepein_atividade2.repositories;

import cepein.cepein_atividade2.domain.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Integer>
{

}
