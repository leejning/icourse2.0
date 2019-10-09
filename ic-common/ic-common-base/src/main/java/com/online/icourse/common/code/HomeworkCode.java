package com.online.icourse.common.code;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

/**
 * @ClassName HomeworkCode
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public enum HomeworkCode implements ResultCode {
    /**
     *
     */
    NOT_EXSIST_PUBLISH_HOMEWORK_IN_THIS_ID(false,21001,"不存在该id的已发布作业"),
    NOT_EXSIST_HOMEWORK_IN_THIS_ID(false,21002,"不存在该id作业"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private HomeworkCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, HomeworkCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, HomeworkCode> builder = ImmutableMap.builder();
        for (HomeworkCode commonCode : values()) {
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
