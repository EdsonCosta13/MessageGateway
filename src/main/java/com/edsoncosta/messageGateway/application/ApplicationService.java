package com.edsoncosta.messageGateway.application;

import com.edsoncosta.messageGateway.application.dto.ApplicationResponseDTO;
import com.edsoncosta.messageGateway.generic.AbstractServiceImpl;
import com.edsoncosta.messageGateway.utils.http.HttpResponseBody;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ApplicationService extends AbstractServiceImpl<Application,UUID, ApplicationResponseDTO> {

    public ApplicationService(ApplicationRepository repository) {
        super(repository);
    }

    @Override
    protected ApplicationRepository getRepository() {
        return (ApplicationRepository)super.getRepository();
    }

    @Override
    public ApplicationResponseDTO mapToResponse(Application application) {

        return new ApplicationResponseDTO(
                application.getId(),
                application.getNome(),
                application.getDescricao(),
                application.getApiKey(),
                application.getBaseUrl(),
                application.getSenderDefault()
                );
    }

    @Override
    public ApplicationResponseDTO save(Application entity) {

        Optional<Application> existsAppName=getRepository().findByNome(entity.getNome());

        if (existsAppName.isPresent()) {
            throw new EntityExistsException("Aplicação com o nome '" + entity.getNome()+ "' já existe!");
        }

        Optional<Application> existsAppUrl=getRepository().findByBaseUrl(entity.getBaseUrl());

        if (existsAppUrl.isPresent()) {
            throw new EntityExistsException("Aplicação com a URL '" + entity.getBaseUrl()+ "' já existe!");
        }

        Optional<Application> existsSender=getRepository().findBySenderDefault(entity.getSenderDefault());

        if (existsSender.isPresent()) {
            throw new EntityExistsException("Aplicação com o SENDER '" + entity.getSenderDefault()+ "' já existe!");
        }

        String apiKey = generateApiKey(entity.getNome(), entity.getBaseUrl());
        entity.setApiKey(apiKey);

        return super.save(entity);
    }

    private String generateApiKey(String nome, String baseUrl) {
        String rawData = nome + baseUrl + System.currentTimeMillis() + new Random().nextInt(999999);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawData.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar API Key: " + e.getMessage(), e);
        }
    }
}
