package com.online.icourse.provider.bean.dto.request;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName AnswerRecordModify
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@Data
public class AnswerRecordModify {

    private String id;
    private Long studentId;
    private String hpId;

    /**
     * 单选
     */
    private Map<String,String> singlechoiceRec;
    /**
     * 多选
     */
    private Map<String,String> multiplechoiceRec;
    /**
     * 判断
     */
    private Map<String,String> judgmentRec;
    /**
     * 填空
     */
    private Map<String,String> completionRec;
    /**
     * 简答
     */
    private Map<String,String> shortanswerRec;
}
