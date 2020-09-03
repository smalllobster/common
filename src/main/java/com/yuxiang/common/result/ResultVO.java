
package com.yuxiang.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/20 <br>
 * 功能描述: 返回结果统一类<br>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

  private Integer code;

  private String msg;
  
  private String actionType;

  private T data;

  private String sign;
}
