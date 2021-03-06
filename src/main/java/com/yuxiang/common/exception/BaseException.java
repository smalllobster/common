
package com.yuxiang.common.exception;

import com.yuxiang.common.enums.ResultEnum;
import lombok.Data;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/20 <br>
 * 功能描述: 异常<br>
 */
@Data
public class BaseException extends RuntimeException {

  private Integer code;

  public BaseException() {
    super(ResultEnum.ERROR.getMsg());
    this.code = ResultEnum.ERROR.getCode();
  }

  public BaseException(ResultEnum resultEnum) {
    super(resultEnum.getMsg());
    this.code = resultEnum.getCode();
  }

  public BaseException(ResultEnum resultEnum, String msg) {
    super(msg);
    this.code = resultEnum.getCode();
  }

  public BaseException(String msg) {
    super(msg);
    this.code = ResultEnum.ERROR.getCode();
  }

  public BaseException(Integer code, String msg) {
    super(msg);
    this.code = code;
  }

  public BaseException(String msg, Exception e) {
    super(msg, e);
    this.code = ResultEnum.ERROR.getCode();
  }

  public BaseException(ResultEnum resultEnum, Exception e) {
    super(resultEnum.getMsg(), e);
    this.code = resultEnum.getCode();
  }

  public BaseException(ResultEnum resultEnum, String msg, Exception e) {
    super(msg, e);
    this.code = resultEnum.getCode();
  }

  public BaseException(Integer code, String msg, Exception e) {
    super(msg, e);
    this.code = code;
  }

}