package com.xuegao.springboot2_3_security.domain;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.domain
 * <br/> @ClassName：RespBean
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 15:11
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}