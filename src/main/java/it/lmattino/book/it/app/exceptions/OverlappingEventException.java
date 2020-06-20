/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.exceptions;

/**
 *
 * @author Mattinò
 */
public class OverlappingEventException extends Exception {

    public OverlappingEventException() {
        super();
    }

    public OverlappingEventException(String message) {
        super(message);
    }

    public OverlappingEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverlappingEventException(Throwable cause) {
        super(cause);
    }

    public OverlappingEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
