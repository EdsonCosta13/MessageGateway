package com.edsoncosta.messageGateway.generic.models;


import com.edsoncosta.messageGateway.utils.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GenericId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updateddAt;

    public GenericId() {
        this.status = Status.ACTIVE;
    }

    public GenericId(UUID id, Status status, LocalDateTime createdAt, LocalDateTime updateddAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.updateddAt = updateddAt;
    }

    public UUID getId() {
        return this.id;
    }

    public Status getStatus() {
        return this.status;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdateddAt() {
        return this.updateddAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateddAt(LocalDateTime updateddAt) {
        this.updateddAt = updateddAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updateddAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateddAt = LocalDateTime.now();
    }
}
