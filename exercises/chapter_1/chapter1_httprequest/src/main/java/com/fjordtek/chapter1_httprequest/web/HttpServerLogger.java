// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter1_httprequest.web;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class HttpServerLogger {

    private LocalDateTime logTimeStamp;

    private void setLogTimeStamp() {
        this.logTimeStamp = LocalDateTime.now();
    }

    private LocalDateTime getLogTimeStamp() {
        return this.logTimeStamp;
    }

    public void logMessageNormal(
            HttpServletRequest request,
            String HttpRawStatusType
    ) {

        setLogTimeStamp();
        System.out.printf(
                "%s: HTTP request to '%s' from client %s (%s)\n",
                getLogTimeStamp(),
                request.getRequestURL(),
                request.getRemoteAddr(),
                HttpRawStatusType
        );
    }

    public void logMessageError(
            HttpServletRequest request,
            String HttpRawStatusType
    ) {

        setLogTimeStamp();
        System.err.printf(
                "%s: Invalid HTTP request to '%s' from client %s (%s)\n",
                getLogTimeStamp(),
                request.getRequestURL(),
                request.getRemoteAddr(),
                HttpRawStatusType
        );
    }

}
