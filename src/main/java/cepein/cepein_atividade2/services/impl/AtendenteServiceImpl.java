package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.services.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendenteServiceImpl implements AtendenteService
{
    @Autowired
    private AtendenteRepository repository;

    @Override
    public Atendente findById(Integer id)
    {
        //todo: escrever um teste de erro para substituir o null
        Optional<Atendente> atendente = repository.findById(id);
        return atendente.orElse(null);
    }
}
