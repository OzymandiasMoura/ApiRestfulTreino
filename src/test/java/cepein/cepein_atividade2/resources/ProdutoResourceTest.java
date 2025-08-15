package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoResourceTest
{
    private Optional<Produto> produtoOptional;
    private ProdutoDto produtoDto;
    private Produto produto;

    private final static Integer id = 1;
    private final static String nome = "Geladeira";
    private final static String barCode = "1111111111111";
    private final static Double preco = 1550.50;
    private final static Boolean ativo = true;

    public static final String notFoundMessage = "Produto não encontrado!";
    public static final String dataIntegrityMessage = "Código de barras já existe no sistema!";
    public static final String invalidFormatMessage = "Formato do código de barras é invalido.";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        produto = new Produto(id,  nome, barCode, preco, ativo);
        produtoOptional = Optional.of(new Produto(id,  nome, barCode, preco, ativo));
        produtoDto = new ProdutoDto(id,  nome, barCode, preco, ativo);
    }

    @Test
    void findById()
    {
    }

    @Test
    void create()
    {
    }

    @Test
    void findAll()
    {
    }

    @Test
    void update()
    {
    }

    @Test
    void findByBarCode()
    {
    }

    @Test
    void delete()
    {
    }

    @Test
    void softDelete()
    {
    }
}