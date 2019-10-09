package com.online.icourse.common.code;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

/**
 * @ClassName AnswerRecordCode
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/7 0007
 * @Version V1.0
 **/
public enum AnswerRecordCode implements ResultCode {
    /**
     *
     */
    STUDENT_HAS_NOT_THIS_HOMEWORK(false,21001,"该学生没有这个作业"),
    NOT_EXSIST_ANSWER_RECORD_IN_THIS_ID(false,21101,"不存在该id的学生作答记录"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private AnswerRecordCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, AnswerRecordCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, AnswerRecordCode> builder = ImmutableMap.builder();
        for (AnswerRecordCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
