package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.services.AtendenteService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtendenteServiceImpl implements AtendenteService
{
    @Autowired
    private AtendenteRepository repository;

    @Override
    public Atendente findById(Integer id)
    {
        Optional<Atendente> atendente = repository.findById(id);
        return atendente.orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado!"));
    }

    @Override
    public Atendente create(AtendenteDto atendente)
    {
        validationByCpf(atendente);
        return repository.save(new Atendente(atendente.getNome(), atendente.getCpf()));
    }

    @Override
    public List<Atendente> findAll()
    {
        return repository.findAll();
    }

    @Override
    public Atendente update(AtendenteDto atendente)
    {
        validationByCpf(atendente);
        return repository.save(new Atendente(atendente.getNome(), atendente.getCpf()));
    }

    @Override
    public Atendente findByCpf(AtendenteDto atendente)
    {
        Optional<Atendente> atendente1 = repository.findByCpf(atendente.getCpf());
        return atendente1.orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado!"));
    }

    @Override
    public void validationByCpf(AtendenteDto atendente)
    {
        Optional<Atendente> optional = repository.findByCpf(atendente.getCpf());
        if(optional.isPresent() && optional.get().getIdAtendente().equals(atendente.getIdAtendente()))
        {
            throw new DataIntegrityException("Cpf já cadastrado no sistema!");
        }
    }


}
