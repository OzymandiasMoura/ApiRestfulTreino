package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AtendenteServiceImplTest
{
    @InjectMocks
    AtendenteServiceImpl service;

    @Mock
    AtendenteRepository repository;

    private Optional<Atendente> atendenteOptional;
    private AtendenteDto atendenteDto;
    private Atendente atendente;

    private final static Integer id = 1;
    private final static String nome = "Pedro";
    private final static String cpf = "11111111111";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        startAtendentes();
    }

    @Test
    void whenFindByIdReturnAtendente()
    {
        Mockito.when(repository.findById(id)).thenReturn(atendenteOptional);

        Atendente response = service.findById(id);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
    }

    @Test
    void whenFindByCpfValidationThenThrowObjectNotFoundException()
    {
        Mockito.when(repository.findByCpf(cpf)).thenThrow(new ObjectNotFoundException("Atendente não encontrado!"));

        try
        {
            service.findById(atendenteDto.getIdAtendente());
        }
        catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Atendente não encontrado!", ex.getMessage());
        }
    }

    private void startAtendentes()
    {
        atendente = new Atendente(id, nome, cpf);
        atendenteDto = new AtendenteDto(id, nome, cpf);
        atendenteOptional = Optional.of(new Atendente(id, nome, cpf));
    }
}