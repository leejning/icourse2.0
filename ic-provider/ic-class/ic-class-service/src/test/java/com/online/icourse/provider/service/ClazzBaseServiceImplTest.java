package com.online.icourse.provider.service;


import com.online.icourse.provider.bean.ClazzBase;
import com.online.icourse.provider.dao.ClazzBaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClazzBaseServiceImplTest {

    @Autowired
    private ClazzBaseDao clazzBaseDao;
    @Test
    public void createClazzBase() {

        List<String> courseName = Arrays.asList("javaee程序设计","数据结构","网页设计与开发","php编程基础","面向对象程序设计c++",
                "Oracle数据库教程","java基础入门","jsp程序设计","计算机组成原理","计算机操作系统","计算机网络",
                "数据库系统原理与设计");
        long cid = 1;
        for (String name:courseName) {
            for (int i = 1; i < 4; i++) {
                ClazzBase base = new ClazzBase();
                base.setClazzBaseName("16软件"+i+"班"+name);
                base.setCourseId(cid);
                base.setDescription("软件工程专业必修课程");
                clazzBaseDao.save(base);
            }
            cid++;
        }

    }
}
