package com.edsoncosta.messageGateway.utils.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class HttpResponseController {

    private HttpResponseBody build(Object data, String message, HttpStatus status){
        return new HttpResponseBody(data, message, status, LocalDateTime.now());
    }

    public ResponseEntity<HttpResponseBody> success(Object data, String message){
        return ResponseEntity.ok(build(data, message, HttpStatus.OK));
    }
    public ResponseEntity<HttpResponseBody> notFound(String message){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(build(null, message, HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<HttpResponseBody> badRequest(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(build(null, message, HttpStatus.BAD_REQUEST));
    }
    public ResponseEntity<HttpResponseBody> created(Object data, String message){
        return ResponseEntity.status(HttpStatus.CREATED).body(build(data, message, HttpStatus.CREATED));
    }

    public ResponseEntity<HttpResponseBody> successNoContent(String message){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(build(null, message, HttpStatus.NO_CONTENT));
    }

}
