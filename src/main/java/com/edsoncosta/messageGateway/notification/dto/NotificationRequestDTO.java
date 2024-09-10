package com.edsoncosta.messageGateway.notification.dto;

import com.edsoncosta.messageGateway.generic.dto.GenericValueRequestDTO;
import com.edsoncosta.messageGateway.notification.Notification;
import com.edsoncosta.messageGateway.utils.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class NotificationRequestDTO extends GenericValueRequestDTO<Notification> {
    @JsonIgnore
    private String nome;
    @JsonIgnore
    private String descricao;
//    private String senderName;
    private String recipientsPhone;
    private NotificationType notificationType;

    private String body;

    @Override
    public Notification mapToEntity() {

        List<String> validationErrors = validate();

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Erros de validação: " + String.join(", ", validationErrors));
        }

        Notification notification=new Notification();
        notification.setRecipientPhone(this.recipientsPhone);
        notification.setBody(this.body);
        notification.setNotificationType(notificationType);

        return notification;
    }

//    public String getSenderName() {
//        return senderName;
//    }
//
//    public void setSenderName(String senderName) {
//        this.senderName = senderName;
//    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Valida os campos obrigatórios do DTO de requisição de notificação.
     *
     * @return List<String> contendo todas as mensagens de erro de validação encontradas.
     */
    public List<String> validate() {

        List<String> errors = new ArrayList<>();

        if (recipientsPhone == null || recipientsPhone.isEmpty()) {
            errors.add("O campo 'recipientPhone' é obrigatório.");
        }

        if (body == null || body.isEmpty()) {
            errors.add("O campo 'body' é obrigatório.");
        }
        if (notificationType == null) {
            errors.add("O campo 'notificationType' é obrigatório.");
        }

//        if(notificationType!=NotificationType.ALERT || notificationType!=NotificationType.INFORMATIVE){
//            errors.add("O campo 'notificationType' permite apenas os seguintes valores: "+"INFORMATIVE ,ALERT");
//        }

        return errors;
    }
}
