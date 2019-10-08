package com.online.icourse.provider.mq;

import com.online.icourse.provider.service.AnswerRecordService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HomeworkListener
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@Component
public class HomeworkListener {
    @Reference(version = "2.0.0")
    private AnswerRecordService answerRecordService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "student.homework.publish.queue", durable = "true"),
            exchange = @Exchange(value = "icourse.homework.exchange",
                    type = ExchangeTypes.TOPIC),
            key = {"homework.publish"}
    ))
    public void listenPublish(String hpId) throws Exception {
        System.out.println("监听到作业发布"+hpId);
        if (hpId == null) {
            return;
        }
        answerRecordService.afterPublishHomeworkCreateRecord(hpId);
    }
}
