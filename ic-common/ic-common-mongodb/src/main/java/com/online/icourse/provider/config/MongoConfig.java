package com.online.icourse.provider.config;

import com.online.icourse.provider.support.CustomSimpleMongoRespriatory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @ClassName MongoConfig
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
@Configuration
@EnableMongoRepositories(basePackages = "com.online.icourse.provider.dao",
        repositoryBaseClass = CustomSimpleMongoRespriatory.class)
public class MongoConfig {
}