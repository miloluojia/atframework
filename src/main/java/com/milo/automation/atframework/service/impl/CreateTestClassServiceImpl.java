package com.milo.automation.atframework.service.impl;

import com.milo.automation.atframework.pojo.req.CreateTestClassReq;
import com.milo.automation.atframework.service.inter.ICreateTestClassService;
import com.milo.automation.atframework.util.JavaClassGenerator;
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Service
public class CreateTestClassServiceImpl implements ICreateTestClassService {

    @Override
    public boolean createTestClass(CreateTestClassReq req) {
        boolean flag = false;
        try {
            JavaClassGenerator.createAndLoadClass(req.getClassName());
            flag = true;
        } catch (NotFoundException | CannotCompileException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IOException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }
}
