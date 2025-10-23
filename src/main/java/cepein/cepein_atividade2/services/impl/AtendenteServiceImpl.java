package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.mapper.AtendenteMapper;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.services.AtendenteService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  AtendenteServiceImpl implements AtendenteService
{
    @Autowired
    private AtendenteRepository repository;

    private final String objectNotFoundMessage =  "Atendente não encontrado!";

    @Override
    public Atendente findById(Integer id)
    {
        Optional<Atendente> atendente = repository.findById(id);
        return atendente.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
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
    public Atendente update(Integer id, AtendenteDto atendenteDto)
    {
        Atendente atendente = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
        atendente.setNome(atendenteDto.getNome());
        atendente.setCpf(atendenteDto.getCpf());
        atendente.setAtivo(atendenteDto.getAtivo());
        validationByCpf(AtendenteMapper.entityToDto(atendente));
        return repository.save(atendente);
    }

    @Override
    public Atendente findByCpf(String cpf)
    {
        Optional<Atendente> atendente1 = repository.findByCpf(cpf);
        return atendente1.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
    }

    @Override
    public void validationByCpf(AtendenteDto atendente)
    {
        final String dataIntegrityMessage = "Cpf já cadastrado no sistema!";
        Optional<Atendente> optional = repository.findByCpf(atendente.getCpf());
        if(optional.isPresent() && !optional.get().getIdAtendente().equals(atendente.getIdAtendente()))
        {
            throw new DataIntegrityException(dataIntegrityMessage);
        }
    }

    @Override
    public void delete(Integer id)
    {
        repository.deleteById(id);
    }

    @Override
    public Atendente softDelete(Integer id)
    {
        Atendente atendente1 = findById(id);
        atendente1.setAtivo(false);
        repository.save(atendente1);

        return atendente1;
    }

    @Override
    public List<Atendente> findByCpfOrIdAtendente(String cpf, Integer idAtendente)
    {
        return repository.findByCpfOrIdAtendente(cpf, idAtendente);
    }
}
