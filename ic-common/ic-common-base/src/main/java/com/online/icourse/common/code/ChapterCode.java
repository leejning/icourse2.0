package com.online.icourse.common.code;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;

/**
 * @ClassName ChapterCode
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
public enum ChapterCode implements ResultCode {
    /**
     *
     */
    ADD_CHAPTER_FAIL(false,20800,"添加章节失败"),
    MODIFY_CHAPTER_INFO_FAIL(false,20801,"修改章节信息失败"),
    DELETE_CHAPTER_INFO_FAIL(false,20802,"删除章节失败"),
    ;

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private ChapterCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, ChapterCode> CACHE;
    static {
        final ImmutableMap.Builder<Integer, ChapterCode> builder = ImmutableMap.builder();
        for (ChapterCode commonCode : values()) {
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
