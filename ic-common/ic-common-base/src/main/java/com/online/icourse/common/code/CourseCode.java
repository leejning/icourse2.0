package com.online.icourse.common.code;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

public enum CourseCode implements ResultCode {
    /**
     *
     */
    ADD_COURSE_FAIL(false,20700,"添加课程失败"),
    MODIFY_COURSE_INFO_FAIL(false,20701,"修改课程信息失败"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private CourseCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, CourseCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, CourseCode> builder = ImmutableMap.builder();
        for (CourseCode commonCode : values()) {
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
