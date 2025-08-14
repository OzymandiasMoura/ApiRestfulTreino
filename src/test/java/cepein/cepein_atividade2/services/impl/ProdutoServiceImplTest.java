package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.repositories.ProdutoRepository;
import cepein.cepein_atividade2.services.ProdutoService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.InvalidFormatException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    ProdutoServiceImpl service;

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
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(produtoOptional);

        Produto response = service.findById(id);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenFindByIdThenException()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findById(id);
        }
        catch (Exception e)
        {
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertNotNull(e);
        }
    }

    @Test
    void whenCreateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(produtoOptional);

        Produto response = service.create(produtoDto);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenCreateThenException()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.create(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void whenCreateThenExceptionInvalidFormat()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new InvalidFormatException(invalidFormatMessage));

        try
        {
            service.create(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(invalidFormatMessage, e.getMessage());
            assertEquals(InvalidFormatException.class, e.getClass());

        }
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