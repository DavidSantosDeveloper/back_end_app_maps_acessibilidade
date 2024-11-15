package com.app.acessibilidade.api.domain.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppErrorMessage {
    public HttpStatus status;
    public String  message;
}