package ru.progtools.exception;

/**
 *
 * @author deonisius
 */
public class QueryCreatorException extends Exception {

    public QueryCreatorException() {
    }

    public QueryCreatorException(String message) {
        super(message);
    }

    public QueryCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryCreatorException(Throwable cause) {
        super(cause);
    }

}
