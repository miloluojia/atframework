package com.milo.automation.atframework.controller;

import com.itranswarp.compiler.JavaStringCompiler;
import com.milo.automation.atframework.dao.mapper.TeacherMapper;
import com.milo.automation.atframework.pojo.doAnddto.TeacherDO;
import com.milo.automation.atframework.util.DynamicallyCreateJavaFile;
import com.sun.tools.javac.resources.compiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
public class TeacherController {

    @Autowired
    TeacherMapper teacherMapper;

    static final String JAVA_SOURCE_CODE = "/* a single java source file */   "
            + "package com.milo.automation.atframework.controller;            "
            + "import org.testng.annotations.Test;                            "
            + "public class UserProxy {                                       "
            + "    boolean _dirty = false;                                    "
            + "    public void setId(String id) {                             "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setName(String name) {                         "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setCreated(long created) {                     "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setDirty(boolean dirty) {                      "
            + "        this._dirty = dirty;                                   "
            + "    }                                                          "
            + "    @Test                                                      "
            + "    public boolean isDirty() {                                 "
            + "        return this._dirty;                                    "
            + "    }                                                          "
            + "}                                                              ";

    @RequestMapping("/getTeacherByid/{Tid}")
    public TeacherDO getTeacherById(@PathVariable String Tid){
        TeacherDO teacherDO =  teacherMapper.getTeacherById(Tid);
        return teacherDO;
    }


    @RequestMapping("/generateJavaCode")
    public boolean generateJavaCode(){
        try {
            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> results = compiler.compile("UserProxy.java", JAVA_SOURCE_CODE);
            Class<?> clazz = compiler.loadClass("com.milo.automation.atframework.controller.UserProxy", results);
//            User user = (User) clazz.newInstance();
            Object userProxy = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return true;
        // try instance:
//        User user = (User) clazz.newInstance();
    }

    @RequestMapping("/generate/{className}")
    public boolean generateJavaFileWithClassName(@PathVariable String className){
        DynamicallyCreateJavaFile mtc = new DynamicallyCreateJavaFile();
        mtc.createIt(className);
        if (mtc.compileIt()) {
            System.out.println("Running " + mtc.getClassName() + ":\n\n");
            mtc.runIt();
            return true;
        } else{
            System.out.println(mtc.getSourceName() + " is bad.");
            return false;
        }
    }

}
