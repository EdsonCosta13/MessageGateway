package com.edsoncosta.messageGateway.utils.handler.generic;


import com.edsoncosta.messageGateway.utils.exception.generic.GenericErrorDTO;
import com.edsoncosta.messageGateway.utils.exception.generic.GenericException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/******************************
 * Created by: Edson Costa
 * Date: 08/07/2024
 * Time: 13:34 AM
 ******************************/


@ControllerAdvice
public class ExceptionHandler {


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(GenericException.class)
    public GenericErrorDTO handler(GenericException ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public GenericErrorDTO handler(EntityNotFoundException ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public GenericErrorDTO genericHandler(Exception ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
