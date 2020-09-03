
package com.yuxiang.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.yuxiang.common.enums.ResultEnum;
import com.yuxiang.common.exception.YXException;
import com.yuxiang.common.util.helper.StringHelper;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 功能说明: 请求参数处理
 * 开发人员: kjp
 * 开发时间: 2018/6/29 <br>
 * 功能描述: 请求参数处理<br>
 */
public class ParamUtil {

  // 整型参数默认值
  private static final int INT_PARAM_DEFAULT = 0;
  // 分页默认条数
  private static final int PAGE_DEFAULT_SIZE = 20;
  // 分页最大条数
  private static final int PAGE_MAX_SIZE = 200;
  // 分页最大条数
/*  private static final List<String> DEC_KEYS = new ArrayList<String>();
  static {
    DEC_KEYS.add("payPassword");
    DEC_KEYS.add("password");
    DEC_KEYS.add("oldPassword");
    DEC_KEYS.add("confirmPwd"); 
  }*/

  /**
   * 获取整型参数（非必须），默认INT_PARAM_DEFAULT
   *
   * @param map
   * @param key
   * @return
   */
  public static int intParam(Map<String, Object> map, String key) {
    return intParam(map, key, INT_PARAM_DEFAULT);
  }

  /**
   * 获取整型参数
   *
   * @param map
   * @param key
   * @param required
   * @return
   */
  public static int intParam(Map<String, Object> map, String key, boolean required) {
    return Integer.parseInt(getParam(map, key, INT_PARAM_DEFAULT, required).toString());
  }

  /**
   * 获取整型参数（非必须），默认defaultValue
   *
   * @param map
   * @param key
   * @param defaultValue
   * @return
   */
  public static int intParam(Map<String, Object> map, String key, int defaultValue) {
    String value = StringHelper.isNull(getParam(map, key, defaultValue));
    if (StringHelper.isBlank(value)) {
      return defaultValue;
    }
    return Integer.parseInt(getParam(map, key, defaultValue).toString());
  }

  /**
   * 获取字符串参数值（必须）
   *
   * @param map
   * @param key
   * @return
   */
  public static String stringParam(Map<String, Object> map, String key) {
    Object value = getParam(map, key, null, true);
    return Objects.isNull(value) ? null : StringUtils.deleteWhitespace(value.toString());
  }

  /**
   * 获取BigDecimal参数值（必须）
   *
   * @param map
   * @param key
   * @return
   */
  public static BigDecimal bigDecimalParam(Map<String, Object> map, String key) {
    Object value = getParam(map, key, null, true);
    return Objects.isNull(value) ? null : BigDecimal.valueOf(Double.parseDouble(value.toString()));
  }

  /**
   * 获取BigDecimal参数值（非必须）
   *
   * @param map
   * @param key
   * @param defaultValue
   * @return
   */
  public static BigDecimal bigDecimalParam(Map<String, Object> map, String key, BigDecimal defaultValue) {
    Object value = getParam(map, key, defaultValue, false);
    return Objects.isNull(value) ? BigDecimal.ZERO : BigDecimal.valueOf(Double.parseDouble(value.toString()));
  }

  /**
   * 获取字符串参数信息（非必须）
   *
   * @param map
   * @param key
   * @param defaultValue
   * @return
   */
  public static String stringParam(Map<String, Object> map, String key, Object defaultValue) {
    Object value = getParam(map, key, defaultValue, false);
    return Objects.isNull(value) ? null : StringUtils.deleteWhitespace(value.toString());
  }

  /**
   * 长整型
   *
   * @param map
   * @param key
   * @param defaultValue
   * @return
   */
  public static long longParam(Map<String, Object> map, String key, long defaultValue) {
    Object value = getParam(map, key, defaultValue, false);
    return Objects.isNull(value) ? 0L : Long.valueOf(value + "");
  }

  /**
   * 对象
   *
   * @param map
   * @param key
   * @param clazz
   * @param <K>
   * @return
   */
  public static <K> K objectParam(Map<String, Object> map, String key, Class<K> clazz) {
    String value = stringParam(map, key, null);
    K k;
    try {
      if (StringHelper.isNotBlank(value)) {
        k = JSONObject.parseObject(value, clazz);
      } else {
        k = null;
      }
    } catch (Exception e) {
      throw new YXException(ResultEnum.PARAM_ERROR, "参数转换错误", e);
    }
    return k;
  }

  /**
   * 转换带泛型的对象
   *
   * @param map
   * @param key
   * @param type
   * @return
   */
  public static Object objectParam(Map<String, Object> map, String key, TypeReference type) {
    String value = stringParam(map, key, null);
    Object obj = null;
    try {
      if (StringHelper.isNotBlank(value)) {
        obj = JSONObject.parseObject(value, type);
      }
    } catch (Exception e) {
      throw new YXException(ResultEnum.PARAM_ERROR, "参数转换错误", e);
    }
    return obj;
  }

  /**
   * 获取参数值（必须）
   *
   * @param map
   * @param key
   * @return
   */
  public static Object getParam(Map<String, Object> map, String key) {
    return getParam(map, key, null, true);
  }

  /**
   * 获取参数信息（非必须）
   *
   * @param map
   * @param key
   * @param defaultValue
   * @return
   */
  public static Object getParam(Map<String, Object> map, String key, Object defaultValue) {
    return getParam(map, key, defaultValue, false);
  }

  /**
   * 获取参数信息
   *
   * @param map
   * @param key
   * @param defaultValue
   * @param required
   * @return
   */
  public static Object getParam(Map<String, Object> map, String key, Object defaultValue, boolean required) {

    if (Objects.isNull(map)) {
      if (required) {
        throw new YXException(ResultEnum.MISSING_PARAM_ERROR, "参数【" + key + "】不能为空");
      }
      return defaultValue;
    }
    if (map.containsKey(key)) {
      Object value = map.get(key);
      if (required && StringHelper.isBlank(StringHelper.isNull(value))) {
        throw new YXException(ResultEnum.MISSING_PARAM_ERROR, "参数【" + key + "】的值不能为空");
      }
      return value;
    } else if (required) {
      throw new YXException(ResultEnum.MISSING_PARAM_ERROR, "参数【" + key + "】不能为空");
    }
    return defaultValue;
  }

  /**
   * 获取分页参数，默认一页10
   *
   * @param map
   * @return
   */
  public static Page pageParam(Map<String, Object> map) {
    return pageParam(map, PAGE_DEFAULT_SIZE);
  }

  /**
   * 获取分页参数
   *
   * @param map
   * @param defaultSize
   * @return
   */
  public static Page pageParam(Map<String, Object> map, int defaultSize) {
    return pageParam(map, defaultSize, PAGE_MAX_SIZE);
  }

  /**
   * 获取分页参数
   *
   * @param map
   * @param defaultSize
   * @param maxSize
   * @return
   */
  public static Page pageParam(Map<String, Object> map, int defaultSize, int maxSize) {
    if (Objects.isNull(map)) {
      map = new HashMap<>();
    }
    int size;
    String pageSize = StringHelper.isNull(map.get("size"));
    if (StringHelper.isBlank(pageSize)) {
      size = PAGE_DEFAULT_SIZE;
    } else {
      size = intParam(map, "size", defaultSize);
    }
    int current = intParam(map, "current");
    size = Math.min(size, maxSize);
    Page page = new Page(current, size);
    return page;
  }

}
