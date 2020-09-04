package com.yuxiang.common.result;

import com.yuxiang.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel("Response通用Bean")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    //全局版本号
    private final String VERSION = "V1.0.0";

    @ApiModelProperty("版本")
    private String version;

    @ApiModelProperty("结果状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    /**
     * 成功提示
     *
     * @return
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg("SUCCESS");
        return result;
    }

    public Result() {

        this.version = VERSION;
    }

    /**
     * 成功提示
     *
     * @param msg 提示信息
     * @return
     */
    public static <T> Result<T> ok(String msg) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMsg(msg);
        return r;
    }

    public static <T> Result<T> okInString(T msg) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setData(msg);
        return r;
    }

    public static Result loginOk(String token) {
        Result<String> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setData(token);
        return r;
    }

    /**
     * 成功提示
     *
     * @param obj data
     * @return
     */
    public static <T> Result<T> ok(T obj) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setData(obj);
        return r;
    }

    /**
     * 错误提示
     *
     * @return
     */
    public static <T> Result<T> error() {
        return error(ResultEnum.ERROR.getCode(), "未知异常，请联系管理员");
    }

    /**
     * 错误提示
     *
     * @param msg 提示信息
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return error(ResultEnum.ERROR.getCode(), msg);
    }

    /**
     * 错误提示
     *
     * @param code 返回码
     * @param msg  提示信息
     * @return
     */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setMsg(msg);
        r.setCode(code);
        r.setData(null);
        return r;
    }

    public static <T> Result<T> error(int code, T data, String msg) {
        Result<T> r = new Result<>();
        r.setMsg(msg);
        r.setData(data);
        r.setCode(code);
        return r;
    }

}