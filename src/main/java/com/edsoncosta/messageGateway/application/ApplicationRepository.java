package com.edsoncosta.messageGateway.application;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    Optional<Application> findByNome(String name);
    Optional<Application> findByBaseUrl(String url);
    Optional<Application> findBySenderDefault(String senderName);
    Optional<Application> findByApiKey(String apiKey);
}
