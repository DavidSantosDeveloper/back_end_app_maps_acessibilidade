package com.app.acessibilidade.api.domain.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.acessibilidade.api.domain.error.*;
import com.app.acessibilidade.api.domain.exception.*;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(AplicativoException.class)
    private ResponseEntity<AppErrorMessage> AplicativoExceptionHandler(AplicativoException exception){
        AppErrorMessage errorMessage=new AppErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
    /* 
     private ResponseEntity<String> AplicativoExceptionHandler(AplicativoException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro generico na aplicacao");
    }
    */
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<AppErrorMessage> RuntimeExceptionHandler(RuntimeException exception){
        AppErrorMessage errorMessage=new AppErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
    
}