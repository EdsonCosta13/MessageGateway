package com.edsoncosta.messageGateway.utils.exception.generic;


import java.util.Objects;

public class GenericException extends RuntimeException{
    private String launcher;
    private String message;

    public GenericException(String message){
        super(message);
        this.message = message;
    }
    public GenericException(String message, String launcher){
        super(message);
        this.message = message;
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
        GenericException that = (GenericException) o;
        return Objects.equals(launcher, that.launcher) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(launcher, message);
    }
}
