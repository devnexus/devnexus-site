package com.devnexus.ting.core.service.exception;

/**
 * @author Gunnar Hillert
 * @version $Id:DuplicateUserException.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class DuplicateUserException extends Exception {


    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5935393816289263497L;

    /**
     * Constructor.
     */
    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserException(Throwable cause) {
        super(cause);
    }
}
