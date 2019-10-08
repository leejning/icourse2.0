package com.online.icourse.provider.service;

import com.online.icourse.provider.dao.ClazzStudentDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ClazzStudentServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class ClazzStudentServiceImpl implements ClazzStudentService{
    @Autowired
    private ClazzStudentDao clazzStudentDao;



}
