
package com.yuxiang.common.util.helper;


import com.yuxiang.common.exception.YXException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;

/**
 * 功能说明: bean工具类
 * 开发人员: kjp
 * 开发时间: 2018/7/19 <br>
 * 功能描述: bean工具类<br>
 */
public class BeanHelper {

  public static Object mapToObject(Map<String, Object> map, Object obj) {
    if (map == null) {
        return null;
    }

    Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      int mod = field.getModifiers();
      if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
        continue;
      }
      field.setAccessible(true);
      try {
        Object value = map.get(field.getName());
        if (Objects.isNull(value)) {
          continue;
        }
        if (Objects.equals(value.getClass(), field.getType())) {
          field.set(obj, value);
          continue;
        }
        switch (field.getType().getName()) {
          case "java.lang.Integer":
            value = Integer.valueOf(value + "");
            break;
        }
        field.set(obj, value);
      } catch (IllegalAccessException e) {
        throw new YXException("数据转换失败");
      }
    }
    return obj;
  }
}
