package com.edsoncosta.messageGateway.notification.dto;

import com.edsoncosta.messageGateway.utils.enums.NotificationType;
import com.edsoncosta.messageGateway.utils.enums.Status;

import java.util.UUID;

public record NotificationResponseDTO(
        UUID id,
        String senderName,
        String recipientPhone,
        String body,
        NotificationType notificationType
) {
}
