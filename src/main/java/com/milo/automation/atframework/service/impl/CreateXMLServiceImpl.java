package com.milo.automation.atframework.service.impl;

import com.milo.automation.atframework.pojo.req.CreateXMLBaseInfoReq;
import com.milo.automation.atframework.service.inter.ICreateXMLService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;


@Service
public class CreateXMLServiceImpl implements ICreateXMLService {

//    生成xml文件存放路径
    private final static String xmlPath = CreateXMLServiceImpl.class.getClassLoader().getResource("testngXml").getPath() + "/";

//    自定义报表监听器的类名
    private final static String listenerClassName = "com.milo.automation.atframework.listener.ExtentTestNGIReporterListener";


    /**
     * @param createXMLBaseInfoReq
     * @return
     */
    @Override
    public boolean createXML(CreateXMLBaseInfoReq createXMLBaseInfoReq) {
        boolean flag = false;
        try {
            // 1、创建document对象
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点rss
            Element suite = document.addElement("suite");
            // 3、向suite节点添加name属性
            suite.addAttribute("name", createXMLBaseInfoReq.getSuiteName());
            // 4、生成子节点及子节点内容
            Element test = suite.addElement("test");
            test.addAttribute("name", createXMLBaseInfoReq.getTestName());
            Element classes = test.addElement("classes");
            Element className = classes.addElement("class");
            className.addAttribute("name", createXMLBaseInfoReq.getClassName());
            Element listeners = suite.addElement("listeners");
            Element listener = listeners.addElement("listener");
            listener.addAttribute("class-name", listenerClassName);
            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");
            // 6、生成xml文件
            System.out.println(xmlPath);
            File file = new File(  xmlPath + createXMLBaseInfoReq.getClassName() + ".xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            flag = true;
            System.out.println("生成 " + createXMLBaseInfoReq.getClassName() + ".xml 成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成 " + createXMLBaseInfoReq.getClassName() +  ".xml 失败");
        }finally {
            return flag;
        }
    }
}
