package com.edsoncosta.messageGateway.utils.http;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class HttpResponseBody {
    private Object data;
    private String message;
    private HttpStatus status;
    //    private HttpStatusCode statusCode;
    private LocalDateTime timestamp;


    public HttpResponseBody(Object data, String message, HttpStatus status, LocalDateTime timestamp) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public HttpResponseBody(Object data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public HttpResponseBody setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public HttpResponseBody setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }


    private HttpResponseBody(HttpResponseBodyBuilder builder) {
        this.data = builder.data;
        this.message = builder.message;
        this.status = builder.status;
        this.timestamp = LocalDateTime.now();
    }

    public static class HttpResponseBodyBuilder {
        private Object data;
        private String message;
        private HttpStatus status;
        //    private HttpStatusCode statusCode;
        private LocalDateTime timestamp;

        public HttpResponseBodyBuilder(Object data, String message) {
            this.data = data;
            this.message = message;
        }

        public HttpResponseBodyBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }



        public HttpResponseBody build() {
            return new HttpResponseBody(this);
        }

    }
}
