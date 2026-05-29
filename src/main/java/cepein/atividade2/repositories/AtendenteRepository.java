package cepein.atividade2.repositories;

import cepein.atividade2.domain.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Integer>
{
    Optional<Atendente> findByCpf(String cpf);
    List<Atendente> findByCpfOrIdAtendente(String cpf, Integer idAtendente);
}
