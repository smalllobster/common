
package com.yuxiang.common.util.helper;

import com.baomidou.mybatisplus.enums.IEnum;
import com.yuxiang.common.enums.CodeEnum;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/27 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class EnumHelper {

  public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
    for (T each : enumClass.getEnumConstants()) {
      if (code.equals(each.getCode())) {
        return each;
      }
    }
    return null;
  }

  public static <T extends IEnum> T getByValue(Integer value, Class<T> enumClass) {
    for (T each : enumClass.getEnumConstants()) {
      if (value.equals(each.getValue())) {
        return each;
      }
    }
    return null;
  }

}
