package com.milo.automation.atframework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

@RestController
public class HelloController {


    @RequestMapping("/")
    public String hello(){
        return "Hello, world!";
    }

    @Test
    public void testController(){
        System.out.println("hello");
    }

}
