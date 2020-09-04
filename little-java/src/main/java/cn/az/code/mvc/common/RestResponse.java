package cn.az.code.mvc.common;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author az
 * @since 09/04/20
 */
public class RestResponse extends HashMap<String, Object> {

    private Integer status;
    private String message;
    private Object data;

    /**
     * 实例化方法
     */
    private static RestResponse getInstance() {
        return new RestResponse();
    }

    public static RestResponse ok() {
        return ok(null);
    }

    public static RestResponse ok(Object data) {
        return getInstance()
                .setStatus(HttpStatus.OK.value())
                .setMessage(HttpStatus.OK.getReasonPhrase())
                .setData(data);
    }

    public static RestResponse err() {
        return err(null);
    }

    public static RestResponse err(Object data) {
        return getInstance()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .setData(data);
    }

    public Integer getStatus() {
        return status;
    }

    public RestResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
