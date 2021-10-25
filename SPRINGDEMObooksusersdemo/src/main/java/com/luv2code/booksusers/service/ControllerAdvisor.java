package com.luv2code.booksusers.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> somethingWentWrong(
            Exception ex) {

      ErrorMessage exceptionResponse=
              new ErrorMessage(ex.getMessage(),
                      "what else do you want to add");


        return new ResponseEntity<>(exceptionResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    class ErrorMessage{
        private String message;
        private String details;

        public ErrorMessage(String message,String details)
        {
            super();
            this.message=message;
            this.details=details;

        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}