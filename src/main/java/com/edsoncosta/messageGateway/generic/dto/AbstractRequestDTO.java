package com.edsoncosta.messageGateway.generic.dto;

/******************************
 * Created by: EdsonCosta
 * Date: 08/07/2024
 * Time: 12:38 PM
 ******************************/

public abstract class AbstractRequestDTO<E> {
    public abstract E mapToEntity();
}
