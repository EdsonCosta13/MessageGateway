package com.edsoncosta.messageGateway.application.dto;

import java.util.UUID;

public record ApplicationResponseDTO(
        UUID applicationId,
        String nome,
        String description,
        String apiKey,
        String baseUrl,
        String senderDefault
) {
}
