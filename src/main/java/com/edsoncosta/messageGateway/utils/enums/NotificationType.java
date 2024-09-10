package com.edsoncosta.messageGateway.utils.enums;

public enum NotificationType {

    INFORMATIVE("Informativa"),
    ALERT("Alerta");

    private String name;

    NotificationType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
