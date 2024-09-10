package com.edsoncosta.messageGateway.utils.exception;


import java.util.Objects;

/******************************
 * Created by: Edson Costa
 * Date: 08/07/2024
 * Time: 13:34 AM
 ******************************/



public class PreconditionException extends RuntimeException {

    private String launcher;
    private String message;

    public PreconditionException(String message){
        super(message);
        this.message = message;
    }

    public PreconditionException(String message, String launcher){
        this(message);
        this.launcher = launcher;
    }




    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreconditionException that = (PreconditionException) o;
        return Objects.equals(launcher, that.launcher) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(launcher, message);
    }
}
