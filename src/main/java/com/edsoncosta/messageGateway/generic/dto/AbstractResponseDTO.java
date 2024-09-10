package com.edsoncosta.messageGateway.generic.dto;



import com.edsoncosta.messageGateway.utils.enums.Status;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.UUID;

/******************************
 * Created by: Edson Costa
 * Date: 07/07/2024
 * Time: 12:40 AM
 ******************************/

public abstract class AbstractResponseDTO<E> {
    private UUID id;
    private String name;
    private String description;

    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updateddAt;

    public abstract E toEntity(AbstractResponseDTO<E> dto);
    public abstract AbstractResponseDTO<E> toResponse(E entity);


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updateddAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updateddAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public AbstractResponseDTO<E> setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractResponseDTO<E> setName(String name) {
        this.name = name;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public AbstractResponseDTO<E> setStatus(Status status) {
        this.status = status;
        return this;
    }


}
