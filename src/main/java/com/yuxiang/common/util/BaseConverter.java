
package com.yuxiang.common.util;

import com.yuxiang.common.exception.YXException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 功能说明: 通用转换器
 * 开发人员: kjp
 * 开发时间: 2018/8/23 <br>
 * 功能描述: 通用转换器<br>
 */
@Slf4j
public class BaseConverter<K, T> {

  public Function<List, List> converter() {
    return entities -> convert(entities);
  }

  public List<T> convert(List<K> entities) {
    return entities.stream()
        .map(entity -> convert(entity))
        .collect(Collectors.toList());
  }

  public T convert(K entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
    Class<T> clazz = (Class<T>) ptype.getActualTypeArguments()[1];
    T t = null;
    try {
      t = clazz.newInstance();
    } catch (Exception e) {
      log.error("转换器类型实例化错误", e);
      throw new YXException();
    }
    beforeCommonConvert(entity, t);
    BeanUtils.copyProperties(entity, t);
    afterCommonConvert(entity, t);
    return t;
  }

  public void beforeCommonConvert(K k, T t) {

  }

  public void afterCommonConvert(K k, T t) {

  }

}
