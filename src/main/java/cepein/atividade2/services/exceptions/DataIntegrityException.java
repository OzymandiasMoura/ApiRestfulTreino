package cepein.atividade2.services.exceptions;

public class DataIntegrityException extends RuntimeException
{
    public DataIntegrityException(String message)
    {
        super(message);
    }
}
