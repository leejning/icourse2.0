package com.online.icourse.common.code;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

/**
 * @ClassName StudentClazzCode
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
public enum StudentClazzCode implements ResultCode {
    /**
     *
     */
    ADD_STUDENT_FAIL(false,20700,"添加学生失败"),
    STUDENT_IS_IN_THIS_CLASS(false,20702,"学生已加入该课堂"),
    MODIFY_STUDENT_INFI_FAIL(false,20701,"修改学生信息失败"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private StudentClazzCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, StudentClazzCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, StudentClazzCode> builder = ImmutableMap.builder();
        for (StudentClazzCode commonCode : values()) {
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
