package com.milo.automation.atframework.service.inter;

import com.milo.automation.atframework.pojo.req.CreateTestClassReq;
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ICreateTestClassService {

    public boolean createTestClass(CreateTestClassReq req);
}
