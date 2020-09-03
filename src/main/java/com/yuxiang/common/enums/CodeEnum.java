
package com.yuxiang.common.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/27 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public interface CodeEnum extends IEnum {
  Integer getCode();

  @Override
  default Serializable getValue() {
    return getCode();
  }
}
