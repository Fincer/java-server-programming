//Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter2_task2_listhandling.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.fjordtek.chapter2_task2_listhandling.domain.Student;

@Controller
public class StudentController {

    protected static final String helloPageURL        = "hello";
    protected static final String studentPageTemplate = "studentlist";

    private HttpServerLogger     httpServerLogger     = new HttpServerLogger();
    private HttpExceptionHandler httpExceptionHandler = new HttpExceptionHandler();

    @RequestMapping(
        value    = helloPageURL,
        method   = RequestMethod.GET
    )
    public String HelloWebFormGet(Model dataModel, HttpServletRequest requestData)
    {

        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("Kate", "Cole" ));
        studentList.add(new Student("Dan",  "Brown"));
        studentList.add(new Student("Mike", "Mars" ));

        dataModel.addAttribute("students", studentList);

        httpServerLogger.logMessageNormal(
             requestData,
             "HTTPOK"
        );

        return studentPageTemplate;
    }

    // Redirect
    @RequestMapping(
        value  = "/",
        method =  RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.FOUND)
    public String redirectToHelloWebForm() {
        return "redirect:" + helloPageURL;
    }

    // Other URL requests
    @RequestMapping(
         value  = "*",
         method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String ErrorWebForm(HttpServletRequest requestData) {
        return httpExceptionHandler.notFoundErrorHandler(requestData);
    }

}
