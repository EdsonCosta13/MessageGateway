package com.edsoncosta.messageGateway.application;

import com.edsoncosta.messageGateway.application.dto.ApplicationRequestDTO;
import com.edsoncosta.messageGateway.application.dto.ApplicationResponseDTO;
import com.edsoncosta.messageGateway.generic.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("application")
public class ApplicationController extends AbstractController<Application, UUID, ApplicationRequestDTO, ApplicationResponseDTO> {
    protected ApplicationController(ApplicationService service) {
        super(service);
    }
}
