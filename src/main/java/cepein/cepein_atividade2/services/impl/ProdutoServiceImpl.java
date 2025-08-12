package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import cepein.cepein_atividade2.services.ProdutoService;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService
{
    private final String notFoundException = "Produto não encontrado";

    @Autowired
    private ProdutoRepository repository;

    @Override
    public Produto findById(Integer id)
    {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(notFoundException));
    }

    @Override
    public Produto create(ProdutoDto dto)
    {
        Produto produto = ProdutoMapper.dtoToEntity(dto);
        return repository.save(produto);
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
