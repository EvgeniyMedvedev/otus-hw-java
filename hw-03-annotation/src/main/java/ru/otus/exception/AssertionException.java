package ru.otus.exception;

/**
 * AssertionException.
 *
 * @author Evgeniy_Medvedev
 */
public class AssertionException extends RuntimeException {

    public AssertionException(){
        super();
    }

    public AssertionException(String msg){
        super(msg);
    }
}