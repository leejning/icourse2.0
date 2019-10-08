package com.online.icourse.business.common;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

public enum ClazzCode implements ResultCode {
    /**
     *
     */
    REMOVE_CLAZZ_BASE_FAIL(false,20800,"删除课堂基本信息失败"),
    MODIFY_CLAZZ_BASE_FAIL(false,20801,"修改课堂基本信息失败"),
    NO_CLAZZ_IN_THIS_ID(false,20801,"不存在该id的课堂"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private ClazzCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, ClazzCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, ClazzCode> builder = ImmutableMap.builder();
        for (ClazzCode commonCode : values()) {
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
