package com.jingjin.common.result;

/**
 * 自定义错误码
 *
 * @author fxab
 * @date 2024/07/17
 */
public enum ErrorCode {


    //成功类编码(2XX)
    SUCCESS(200,"成功"),
    //客户端错误类（4XX）
    VALIDATE_FAILED(40000,"参数检验失败"),
    TOKEN_MISSION(40010,"Token丢失"),
    TOKEN_INVALID(40020,"Token无效"),
    PRTMISSION_ERROR(40030,"您无权进行此操作"),
    GONE_ERROR(40100,"请求资源已删除"),
    PARAMS_ERROR(40090,"登录参数错误"),
    // 服务端错误类（5XX）
    REGISTER(50001,"注册失败"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    DATABASE_ERROR(50020, "数据库操作失败"),
    LOGOUT_ERROR(50030,"登出失败"),
    SENDEMAIL_ERROR(50040,"邮箱验证码发送失败"),
    // 自定义错误类（6XX）
    PICTURE_MISSION(60010,"oss连接失败");

    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;

    /**
     * 错误信息
     *
     * @param code    错误码
     * @param message 信息
     */
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取错误码
     *
     * @return int
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取消息
     *
     * @return {@link String}
     */
    public String getMessage() {
        return message;
    }
}
