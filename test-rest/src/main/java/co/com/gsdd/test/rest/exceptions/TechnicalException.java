package co.com.gsdd.test.rest.exceptions;

public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = -5396536454958145581L;

    public TechnicalException(String mensaje) {
        super(mensaje);
    }

}