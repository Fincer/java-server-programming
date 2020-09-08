//Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter2_task1_thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HttpController {

    protected static final String helloPageTemplateURL   = "hello";
    
    protected static final String helloPageDefaultName   = "Noname";
    protected static final String helloPageDefaultAge    = "0";

    private HttpServerLogger     httpServerLogger        = new HttpServerLogger();
    private HttpExceptionHandler httpExceptionHandler    = new HttpExceptionHandler();
   
    // User input values: Handle situation where user access URL with required parameters
    @RequestMapping(
         value    = helloPageTemplateURL,
         method   = RequestMethod.GET,
         params   = { "name", "age" }

    )
    public String HelloWebForm(
        @RequestParam(
            value        = "name",
            required     = true
            //defaultValue = "Noname"
        ) String name,
        @RequestParam(
            value        = "age",
            required     = true
            //defaultValue = "0"
        ) String age,
        HttpServletRequest requestData
    ) {

        // Preliminary checks
        if (name == null || age == null ||
            requestData.getParameterMap().size() > 2
        ) {
        	return "Give two GET parameters: 'name' and 'age'\n";
            //return httpExceptionHandler.notFoundErrorHandler(requestData);
        }

        httpServerLogger.logMessageNormal(
            requestData,
            "HTTPOK"
        );

        return helloPageTemplateURL;
    }
    
    // Supply default values: Handle situation where user access URL without required parameters
    @RequestMapping(
        value    = helloPageTemplateURL,
        method   = RequestMethod.GET
    )
    public String HelloWebNullForm() {
       	return "redirect:" + helloPageTemplateURL + "?name=" + helloPageDefaultName + "&age=" + helloPageDefaultAge;
    }
    
    // Redirect: Handle situation where user access root URL
    @RequestMapping(
         value  = "/",
         method =  RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.FOUND)
    public String redirectToHelloWebForm() {
        return this.HelloWebNullForm();
    }
 
    @RequestMapping(
         value  = "*",
         method = RequestMethod.GET
    )
    public String ErrorWebForm(HttpServletRequest requestData) {
        return httpExceptionHandler.notFoundErrorHandler(requestData);
    }
 
}
