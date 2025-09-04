package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import cepein.cepein_atividade2.services.ProdutoService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService
{
    private final String notFoundException = "Produto não encontrado";
    //private final String invalidFormatExceptionMessage = "Código de barras é invalido.";

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
        validationByBarCode(dto);
        Produto produto = ProdutoMapper.dtoToEntity(dto);
        return repository.save(produto);
    }

    @Override
    public List<Produto> findAll()
    {
        return repository.findAll();
    }

    @Override
    public Produto update(ProdutoDto dto)
    {
        validationByBarCode(dto);
        return repository.save(ProdutoMapper.dtoToEntity(dto));
    }

    @Override
    public Produto findByBarCode(String barCode)
    {
        Optional<Produto> findByBarCode = repository.findByBarCode(barCode);
        return findByBarCode.orElseThrow(() -> new ObjectNotFoundException(notFoundException));
    }

    @Override
    public void validationByBarCode(ProdutoDto dto)
    {
        final String dataIntegrityExceptionMessage = "Produto já cadastrado";
        Optional<Produto> produto = repository.findByBarCode(dto.getBarCode());
        if(produto.isPresent() && !produto.get().getIdProduto().equals(dto.getIdProduto()))
        {
            throw  new DataIntegrityException(dataIntegrityExceptionMessage);
        }
    }

//    public void validateBarCodeFormat(ProdutoDto produtoDto)
//    {
//        //Quebrado!!!!!
//        Optional<Produto> produto = repository.findByBarCode(produtoDto.getBarCode());
//        Integer tamanho = produto.get().getBarCode().length();
//        if (!( tamanho == 13) && !(tamanho == 8))
//        {
//            throw  new InvalidFormatException(invalidFormatExceptionMessage);
//        }
//    }

    @Override
    public void delete(Integer id)
    {
        repository.deleteById(id);
    }

    @Override
    public Produto softDelete(ProdutoDto atendente)
    {
        validationByBarCode(atendente);
        Produto produto = ProdutoMapper.dtoToEntity(atendente);
        produto.setAtivo(false);
        return produto;
    }
}
