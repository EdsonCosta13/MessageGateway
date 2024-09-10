package com.edsoncosta.messageGateway.utils.enums;

public enum Channels {
    EMAIL("Email"),
    WHATSAPP("WhatsApp"),
    SMS("SMS");

    private String name;

    Channels(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
