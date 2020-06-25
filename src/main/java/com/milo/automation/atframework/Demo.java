package com.milo.automation.atframework;


import org.testng.annotations.Test;

public class Demo {


    @Test
    public void test1(){
        System.out.println("执行了test1方法");
    }

    @Test
    public void test2(){
        throw new RuntimeException();
    }

}
