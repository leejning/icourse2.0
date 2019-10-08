package com.online.icourse.business.common;

import com.google.common.collect.ImmutableMap;
import com.online.icourse.common.model.response.ResultCode;
/** 
 * @ClassName ProfileCode
 * @Description: TODO 
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0.0
**/
public enum ProfileCode implements ResultCode {
    /**
     * 修改信息失败
     */
    PROFILE_MODIFY_INFO_FAIL(false,24001,"修改信息失败"),
    PROFILE_MODIFY_PASSWORD_FAIL(false,24002,"修改密码失败"),
    PROFILE_PASSWORD_NOT_SAME(false,24003,"两次密码不一致"),
    ;
    boolean success;
    int code;
    String message;

    private ProfileCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, ProfileCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, ProfileCode> builder = ImmutableMap.builder();
        for (ProfileCode commonCode : values()) {
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
