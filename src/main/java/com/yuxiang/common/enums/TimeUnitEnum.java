
package com.yuxiang.common.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2020/09/03 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Getter
@AllArgsConstructor
public enum TimeUnitEnum implements IEnum {
  YEAR(0, "年"),
  MONTH(1, "月"),
  DAY(2, "日"),
  HOUR(3, "时"),
  MINUTE(4, "分"),
  SECOND(5, "秒");

  private Integer code;
  private String desc;

  @JsonValue
  public Integer getValue() {
    return this.code;
  }
}
