package cn.iu.admin.result;

import cn.iu.admin.enums.RstStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Author: aliangcode
 * Date: 2023/7/15 2:49
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(RstStatus status, T data) {
        this(status, null, data);
    }

    public Result(RstStatus rstStatus, String message, T data) {
        if (rstStatus != null) {
            this.code = rstStatus.getCode();
            this.message = rstStatus.getDesc();
        }
        if (!"".equals(message)) {
            this.message = message;
        }
        if (data != null) {
            this.data = data;
        }
    }

    public Result(RstStatus rstStatus) {
        this.code = rstStatus.getCode();
        this.message = rstStatus.getDesc();
    }


    public static <T> Result<T> ok() {
        return new Result<>(RstStatus.ok);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return new Result<>(RstStatus.ok, msg, data);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(RstStatus.ok,data);
    }

    public static <T> Result<T> error(RstStatus status) {
        return new Result<>(status);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
