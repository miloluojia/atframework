package com.milo.automation.atframework.controller;

import com.milo.automation.atframework.pojo.req.CreateTestClassReq;
import com.milo.automation.atframework.service.inter.ICreateTestClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CreateTestClassController {

    @Autowired
    @Qualifier("createTestClassServiceImpl")
    private ICreateTestClassService createTestClassService;


    @RequestMapping(value = "/rest/com/milo/automation/atframework/pojo/req/CreateTestClassReq", method = RequestMethod.POST)
    public boolean createTestClass(@RequestBody CreateTestClassReq req){
        return createTestClassService.createTestClass(req);
    }

}
