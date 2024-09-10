package com.edsoncosta.messageGateway.notification;

import com.edsoncosta.messageGateway.application.ApplicationRepository;
import com.edsoncosta.messageGateway.notification.dto.NotificationRequestDTO;
import com.edsoncosta.messageGateway.notification.dto.NotificationResponseDTO;
import com.edsoncosta.messageGateway.utils.http.HttpResponseBody;
import com.edsoncosta.messageGateway.utils.http.HttpResponseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("notification")
public class NotificationController extends HttpResponseController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<HttpResponseBody> create(@RequestBody NotificationRequestDTO dto,
                                                   @RequestParam("api_key") String apiKey,
                                                   @RequestParam("base_url") String baseUrl
                                                   ) {
        NotificationResponseDTO response=this.notificationService.saveNotification(dto,apiKey,baseUrl);
        return success(response, "Notificação enviada com sucesso!");
    }

    @GetMapping("/getAllByApiKey")
    public ResponseEntity<HttpResponseBody> findByDestaqueId(@RequestParam String api_key) {
        List<NotificationResponseDTO> notifications = notificationService.getByApiKey(api_key);
        return success(notifications, "Notificações carregados com sucesso!");
    }
}
