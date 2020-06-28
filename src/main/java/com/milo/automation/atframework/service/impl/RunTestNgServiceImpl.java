package com.milo.automation.atframework.service.impl;

import com.milo.automation.atframework.Demo;
import com.milo.automation.atframework.service.inter.IRunTestNgService;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


@Service
public class RunTestNgServiceImpl implements IRunTestNgService {

    /**
     * @param xmlName 需要运行的xml文件名
     * @return
     */
    @Override
    public boolean runWithXMl(String xmlName) {

        boolean flag = false;
        try{
            System.setProperty( "org.uncommons.reportng.escape-output", "false");
            List<String> suites = new ArrayList<String>();
            suites.add(Demo.class.getClassLoader().getResource("testngXml").getPath() + "/" + xmlName);
            TestNG tng = new TestNG();
            tng.setTestSuites(suites);
            tng.run();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {

    }
}
