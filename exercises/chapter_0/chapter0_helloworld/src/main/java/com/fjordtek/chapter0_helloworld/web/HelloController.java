// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter0_helloworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloController {

    @RequestMapping("*")
    public String hello() {
        return "Hello Spring\n";
    }

}