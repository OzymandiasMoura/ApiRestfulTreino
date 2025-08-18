package cepein.cepein_atividade2.resources.exceptions;

import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.InvalidFormatException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest
{
    public static final String errorMessage = "Objeto não encontrado.";
    public static final String errorMessage2 = "Objeto ja cadastrado";
    public static final String errorMessage3 = "Formato invalido";

    @InjectMocks
    ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handlerObjectNotFoundException()
    {
        ResponseEntity<StandardError> response = resourceExceptionHandler.handlerObjectNotFoundException(new ObjectNotFoundException(errorMessage), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(errorMessage, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void handlerDataIntegrityException()
    {
        ResponseEntity<StandardError> response = resourceExceptionHandler.handlerDataIntegrityException(new DataIntegrityException(errorMessage2), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(errorMessage2, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }

    @Test
    void handlerInvalidFormatException()
    {
        ResponseEntity<StandardError> response = resourceExceptionHandler.handlerInvalidFormatException(new InvalidFormatException(errorMessage3), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(errorMessage3, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}