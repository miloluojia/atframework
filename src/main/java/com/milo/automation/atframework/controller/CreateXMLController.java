package com.milo.automation.atframework.controller;


import com.milo.automation.atframework.pojo.req.CreateXMLBaseInfoReq;
import com.milo.automation.atframework.service.inter.ICreateXMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateXMLController {

    @Autowired
    @Qualifier("createXMLServiceImpl")
    private ICreateXMLService createXMLService;

    /**
     * 作用：生成testng的xml执行文件，并关联到报表
     * @param createXMLBaseInfoReq
     * @return true表示成功，false表示失败
     */
    @RequestMapping(value = "/rest/com/milo/automation/atframework/pojo/req/CreateXMLBaseInfoReq",method = RequestMethod.POST)
    public boolean createXML(@RequestBody CreateXMLBaseInfoReq createXMLBaseInfoReq){
        return createXMLService.createXML(createXMLBaseInfoReq);
    }


}
