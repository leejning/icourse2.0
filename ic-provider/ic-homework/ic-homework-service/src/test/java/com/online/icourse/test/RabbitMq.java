package com.online.icourse.test;

import com.online.icourse.provider.HomeworkApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RabbitMq
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@SpringBootTest(classes = {HomeworkApplication.class})
@RunWith(SpringRunner.class)
public class RabbitMq {
    @Autowired
    private AmqpTemplate amqpTemplate;



    @Test
    public void testMQ(){
        amqpTemplate.convertAndSend("homework.publish","aksjfhjkleqwnlasknc");
    }


}
