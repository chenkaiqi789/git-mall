package com.gec.mall.domain;

/**
 * 封装 返回类型数据异常提示
 */
public class RespBean {

    private Integer code;
    private String message;
    private Object data;

    private RespBean(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private RespBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RespBean ok(String message) {
        return new RespBean(200, message);
    }

    public static RespBean ok(String message, Object data) {
        return new RespBean(200, message, data);
    }

    public static RespBean error(String message) {
        return new RespBean(200, message);
    }

    public static RespBean error(String message, Object data) {
        return new RespBean(200, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
