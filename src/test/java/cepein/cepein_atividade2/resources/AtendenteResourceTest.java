package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import cepein.cepein_atividade2.services.impl.AtendenteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AtendenteResourceTest
{
    @InjectMocks
    AtendenteResource resource;

    @Mock
    AtendenteServiceImpl service;

    @Mock
    AtendenteRepository repository;

    private AtendenteDto atendenteDto;
    private Atendente atendente;

    private final static Integer id = 1;
    private final static String nome = "Pedro";
    private final static String cpf = "11111111111";
    private final static Boolean ativo = true;

    public static final String notFoundMessage = "Atendente não encontrado!";
    public static final String dataIntegrityMessage = "Cpf já existe no sistema!";



    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        startAtendentes();
    }

    void startAtendentes()
    {
        atendente = new Atendente(id, nome, cpf, ativo);
        atendenteDto = new AtendenteDto(id, nome, cpf, ativo);
    }

    @Test
    void whenFindByIdThenReturnOk()
    {
        Mockito.when(service.findById(Mockito.anyInt())).thenReturn(atendente);

        ResponseEntity<AtendenteDto> response = resource.findById(id);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atendenteDto, response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(atendenteDto.getClass(), response.getBody().getClass());
        assertEquals(id, response.getBody().getIdAtendente());
        assertEquals(nome, response.getBody().getNome());
        assertEquals(cpf, response.getBody().getCpf());
        assertEquals(ativo, response.getBody().getAtivo());
    }

    @Test
    void whenFindByIdThenException()
    {
        Mockito.when(service.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            resource.findById(id);
        }
        catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(notFoundMessage, ex.getMessage());
            assertEquals(ObjectNotFoundException.class, ex.getClass());
        }
    }

    @Test
    void createAtendente()
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
    void delete()
    {
    }

    @Test
    void softDelete()
    {
    }
}