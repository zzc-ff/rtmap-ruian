package com.rtmap.common.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * @author CuiZheng
 * @title: Result
 * @description:
 * @date 2019/3/27 12:03
 */
public class MiddlgrouResult {
    private static final long serialVersionUID = 1348172152215944560L;

    /**
     * 返回状态码，200为正确，100为失败
     */
    private String code;

    /**
     * 返回处理信息，成功或者失败
     */
    private String msg;

    /**
     * 成功返回true，失败返回false
     */
    private Boolean success;

    /**
     * 返回给前端的数据
     */
    private Map<String, Object> extend = new HashMap<String ,Object>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
