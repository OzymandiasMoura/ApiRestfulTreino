package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import cepein.cepein_atividade2.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProdutoServiceImpl implements ProdutoService
{
    @Autowired
    private ProdutoRepository repository;

    @Override
    public Produto findById(Integer id)
    {
        return null;
    }

    @Override
    public Produto create(ProdutoDto atendente)
    {
        return null;
    }

    @Override
    public List<Produto> findAll()
    {
        return List.of();
    }

    @Override
    public Produto update(ProdutoDto atendente)
    {
        return null;
    }

    @Override
    public Produto findByBarCode(String cpf)
    {
        return null;
    }

    @Override
    public void validationByBarCode(String barCode)
    {

    }

    @Override
    public void delete(Integer id)
    {

    }

    @Override
    public Produto softDelete(ProdutoDto atendente)
    {
        return null;
    }
}
