package com.edsoncosta.messageGateway.notification;

import com.edsoncosta.messageGateway.application.Application;
import com.edsoncosta.messageGateway.application.ApplicationRepository;
import com.edsoncosta.messageGateway.generic.AbstractServiceImpl;
import com.edsoncosta.messageGateway.notification.dto.NotificationRequestDTO;
import com.edsoncosta.messageGateway.notification.dto.NotificationResponseDTO;
import com.edsoncosta.messageGateway.utils.exception.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationService extends AbstractServiceImpl<Notification, UUID, NotificationResponseDTO> {

    @Autowired
    private ApplicationRepository applicationRepository;

    public NotificationService(NotificationRepository repository) {
        super(repository);
    }

    @Override
    protected NotificationRepository getRepository() {
        return (NotificationRepository)super.getRepository();
    }

    @Override
    public NotificationResponseDTO mapToResponse(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getSenderName(),
                notification.getRecipientPhone(),
                notification.getBody(),
                notification.getNotificationType()
        );
    }


    /**
     * Salva uma notificação após validar a API Key e associar a aplicação correcta.
     *
     * @param notificationRequestDTO Dados da notificação recebida na requisição.
     * @param apiKey Chave API para verificar a aplicação.
     * @return NotificationResponseDTO contendo a notificação salva.
     */
    public NotificationResponseDTO saveNotification(NotificationRequestDTO notificationRequestDTO, String apiKey,String baseUrl) {
        Optional<Application> applicationOpt = applicationRepository.findByApiKey(apiKey);

        if (applicationOpt.isEmpty()) {
            throw new EntityNotFoundException("API Key inválida!");
        }

        Optional<Application> baseUrlOpt=applicationRepository.findByBaseUrl(baseUrl);

        if (baseUrlOpt.isEmpty()) {
            throw new EntityNotFoundException("Base Url inválida!");
        }

        Application application = applicationOpt.get();

        if(!application.getBaseUrl().equals(baseUrl)){
            throw new UnauthorizedException("A URL base fornecida não corresponde à aplicação associada à API Key!");
        }

        Notification notification = notificationRequestDTO.mapToEntity();

        notification.setSenderName(application.getSenderDefault());
        notification.setApplication(application);

        Notification savedNotification = getRepository().save(notification);

        return mapToResponse(savedNotification);
    }

    public List<NotificationResponseDTO> getByApiKey(String apiKey) {
        Application application=applicationRepository.findByApiKey(apiKey)
                .orElseThrow(()->new EntityNotFoundException("API KEY not found!"));

        List<Notification> notifications=getRepository().findByApplicationApiKey(application.getApiKey());

        if(notifications.isEmpty()){
             return Collections.emptyList();
        }

        return notifications.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
