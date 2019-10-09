package com.online.icourse.common.code;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

/**
 * @ClassName ExerciseCode
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public enum ExerciseCode implements ResultCode {
    /**
     *
     */
    NOT_EXSIST_EXERCISE_IN_THIS_ID(false,20901,"不存在该id的习题"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private ExerciseCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, ExerciseCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, ExerciseCode> builder = ImmutableMap.builder();
        for (ExerciseCode commonCode : values()) {
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
