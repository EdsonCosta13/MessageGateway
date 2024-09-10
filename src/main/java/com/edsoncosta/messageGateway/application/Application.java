package com.edsoncosta.messageGateway.application;

import com.edsoncosta.messageGateway.generic.models.GenericValue;
import com.edsoncosta.messageGateway.notification.Notification;
import com.edsoncosta.messageGateway.utils.enums.Channels;
import com.edsoncosta.messageGateway.utils.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Application extends GenericValue {
    private String apiKey;
    private String baseUrl;
    private String senderDefault;

    @OneToMany(mappedBy = "application")
    private List<Notification> notifications = new ArrayList<>();
}
