package com.edsoncosta.messageGateway.notification;

import com.edsoncosta.messageGateway.application.Application;
import com.edsoncosta.messageGateway.generic.models.GenericId;
import com.edsoncosta.messageGateway.utils.enums.NotificationType;
import com.edsoncosta.messageGateway.utils.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.edsoncosta.messageGateway.utils.enums.Status.ACTIVE;

@Entity
@Setter
@Getter
public class Notification extends GenericId {

    private String senderName;
    private String recipientPhone;
    private String body;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Status status=ACTIVE;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;
}
