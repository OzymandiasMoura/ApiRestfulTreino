package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import java.util.List;

public interface ProdutoService
{
    Produto findById(Integer id);
    Produto create(ProdutoDto atendente);
    List<Produto> findAll();
    Produto update(ProdutoDto atendente);
    Produto findByBarCode(String barCode);
    void validationByBarCode(String barCode);
    void delete(Integer id);
    Produto softDelete(ProdutoDto atendente);
}
