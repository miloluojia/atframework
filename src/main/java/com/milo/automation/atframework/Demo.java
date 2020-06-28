package com.milo.automation.atframework;


import com.milo.automation.atframework.service.impl.CreateXMLServiceImpl;
import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Demo {


    @Test
    public void test1(){
        System.out.println("执行了test1方法");
    }

    @Test
    public void test2(){
        throw new RuntimeException();
    }



    public static void main(String[] args) {
        System.setProperty( "org.uncommons.reportng.escape-output", "false");
        List<String> suites = new ArrayList<String>();
        System.out.println(suites);
//        /Users/milo/atframework/src/main/resources/testngXml/test.xml
//        CreateXMLServiceImpl.class.getClassLoader().getResource("testngXml").getPath() + "/
//        suites.add(System.getProperty("user.dir") + "/mytestng.xml");
        suites.add(Demo.class.getClassLoader().getResource("testngXml").getPath() + "/test.xml");
        TestNG tng = new TestNG();
        tng.setTestSuites(suites);
        tng.run();
    }


}
