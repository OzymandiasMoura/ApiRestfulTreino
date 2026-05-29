package cepein.atividade2.repositories;

import cepein.atividade2.domain.Atendente;
import cepein.atividade2.domain.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Integer>
{
    Optional<Mesa> findMesaByAtendente(Atendente atendente);
}
