package com.edsoncosta.messageGateway.application.dto;

import com.edsoncosta.messageGateway.application.Application;
import com.edsoncosta.messageGateway.generic.dto.GenericValueRequestDTO;

public class ApplicationRequestDTO extends GenericValueRequestDTO<Application> {

    private String baseUrl;
    private String senderDefault;

    @Override
    public Application mapToEntity() {

        Application application=new Application();
        application.setNome(getNome());
        application.setDescricao(getDescricao());
        application.setBaseUrl(this.baseUrl);
        application.setSenderDefault(this.senderDefault);

        return application;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSenderDefault() {
        return senderDefault;
    }

    public void setSenderDefault(String senderDefault) {
        this.senderDefault = senderDefault;
    }
}
