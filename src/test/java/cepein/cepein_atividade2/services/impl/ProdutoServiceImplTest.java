package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import cepein.cepein_atividade2.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceImplTest
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

    @InjectMocks
    ProdutoService service;

    @Mock
    ProdutoRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        produto = new Produto(id, nome, barCode, preco, ativo);
        produtoOptional = Optional.of(new Produto(id, nome, barCode, preco, ativo));
        produtoDto = new ProdutoDto(id, nome, barCode, preco, ativo);
    }

    @Test
    void whenFindByIdThenSuccess()
    {
    }

    @Test
    void whenFindByIdThenException()
    {
    }

    @Test
    void whenCreateThenSuccess()
    {
    }

    @Test
    void whenCreateThenException()
    {
    }

    @Test
    void whenFindAllThenSuccess()
    {
    }

    @Test
    void whenUpdateThenSuccess()
    {
    }

    @Test
    void whenUpdateThenException()
    {
    }

    @Test
    void whenFindByCpfThenSuccess()
    {
    }

    @Test
    void whenFindByCpfThenException()
    {
    }

    @Test
    void whenValidationByCpfThenExeption()
    {
    }

    @Test
    void whenDeleteThenSuccess()
    {
    }

    @Test
    void whenDeleteThenException()
    {
    }

    @Test
    void whenSoftDeleteThenSuccess()
    {
    }

    @Test
    void whenSoftDeleteThenException()
    {
    }
}