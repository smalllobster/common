
package com.yuxiang.common.util;

import com.yuxiang.common.exception.YXException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: 开发人员: kjp
 * 开发时间: 2018年6月27日 <br>
 * 功能描述: <br>
 */
@Slf4j
public class ObjectUtil {
  /**
   * 将Object转换map
   *
   * @param obj
   * @param alias 扩展名
   * @return
   */
  public static Map<String, Object> objectToMap(Object obj, String alias) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (obj == null) {
      return null;
    }
    Field[] parentList = obj.getClass().getSuperclass().getDeclaredFields();
    Field[] fieldList = obj.getClass().getDeclaredFields();
    Field[] allFieldList = (Field[]) ArrayUtils.addAll(parentList, fieldList);
    for (Field field : allFieldList) {
      Method getMethod;
      Object object = null;
      String getMethodName = new StringBuilder("get").append(field.getName().toUpperCase().charAt(0)).append(field.getName().substring(1)).toString();
      try {
        if (!Modifier.isStatic(field.getModifiers())) {
          getMethod = obj.getClass().getMethod(getMethodName);
          object = getMethod.invoke(obj);
        }
      } catch (Exception e) {
        log.debug("objectToMap转换异常不做处理，继续执行！");
      }
      if (null != object) {
        if (null != alias && !alias.equals("")) {
          map.put(new StringBuilder(alias).append("_").append(field.getName()).toString(), object);
        } else {
          map.put(field.getName(), object);
        }
      }
    }
    return map;
  }

  /**
   * 将map转换成bean
   *
   * @param clazz
   * @param data
   * @return
   * @throws Exception
   */
  public static <T> T mapToBean(Map data, Class<T> clazz) {
    try {
      Method[] methods = clazz.getDeclaredMethods();
      T javabean = clazz.newInstance();
      for (Method method : methods) {
        if (method.getName().startsWith("set")) {
          String field = method.getName();
          field = field.substring(field.indexOf("set") + 3);
          field = field.toLowerCase().charAt(0) + field.substring(1);
          method.invoke(javabean, new Object[]{(
              data.get(field) != null
                  && !StringUtils.isEmpty(data.get(field).toString())) ? data.get(field) : (data.get(field.toUpperCase()) != null
              && !StringUtils.isEmpty(data.get(field.toUpperCase()).toString()) ? data.get(field.toUpperCase()) : data.get(field.toLowerCase()))
          });
        }
      }
      return javabean;
    } catch (Exception e) {
      throw new YXException("转换失败", e);
    }
  }

}
