package com.edsoncosta.messageGateway.utils.enums;


/******************************
 * Created by: Edson Costa
 * Date: 08/07/2024
 * Time: 12:14 AM
 ******************************/


public enum Status {
    DEACTIVE("Desativado"),
    ACTIVE("Ativo"),
    CREATED("Criado"),
    SUSPENSED("Suspenso"),
    DELETED("Deletado"),
    BLOCKED("Bloqueado"),
    DONE("Feito"),
    USED("usado"),
    IN_PROGRESS("Em progresso"),
    IN_USE("Em uso"),
    PAID("Pago"),
    EXPIRED("Expirado"),
    PENDING("Pendente"),
    SENT("Enviada"),
    FAIL("Falha"),
    STOPPED("Parado");
    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
