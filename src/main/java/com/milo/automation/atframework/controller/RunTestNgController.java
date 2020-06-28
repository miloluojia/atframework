package com.milo.automation.atframework.controller;

import com.milo.automation.atframework.pojo.req.RunTestNgReq;
import com.milo.automation.atframework.service.inter.IRunTestNgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunTestNgController {

    @Autowired
    @Qualifier("runTestNgServiceImpl")
    private IRunTestNgService runTestNgService;

    @RequestMapping(value = "/rest/com/milo/automation/atframework/pojo/req/RunTestNgReq",method = RequestMethod.POST)
    public boolean runTestNg(@RequestBody RunTestNgReq req){
       return runTestNgService.runWithXMl(req.getXmlName());
    }
}
