package cepein.cepein_atividade2.domain.mapper;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;

public class ProdutoMapper
{
    public static ProdutoDto entityToDto(Produto p)
    {
        ProdutoDto dto = new ProdutoDto(p.getIdProduto(), p.getNome(), p.getBarCode(), p.getPreco(), p.getAtivo());
        return dto;
    }

    public static Produto dtoToEntity(ProdutoDto dto)
    {
        Produto p = new Produto(dto.getNome(), dto.getBarCode(), dto.getPreco(), dto.getAtivo());
        return p;
    }
}
