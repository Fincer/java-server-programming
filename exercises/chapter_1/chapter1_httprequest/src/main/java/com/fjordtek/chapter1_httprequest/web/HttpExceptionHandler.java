// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter1_httprequest.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HttpExceptionHandler {

    private static final String HTTPNOTFOUND  = "Invalid request";

    private HttpServerLogger httpServerLogger = new HttpServerLogger();

    // HTTP status 404
    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason= HTTPNOTFOUND
    )
    @ResponseBody
    public String httpNotFoundBody() {
        return HTTPNOTFOUND + "\n";
    }
    // Very simple exception handler (not any sophistication)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> notFoundErrorHandler(HttpServletRequest requestData) {

        httpServerLogger.logMessageError(
                requestData,
                "HTTPNOTFOUND"
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(HTTPNOTFOUND + "\n");
    }

}
