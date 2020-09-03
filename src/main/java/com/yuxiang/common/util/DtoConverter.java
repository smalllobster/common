
package com.yuxiang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * DTO转换
 * 功能说明: kjp
 * 开发时间: 2018年6月29日 <br>
 * 功能描述: <br>
 */
@Slf4j
public class DtoConverter<K, T> {
  public T toDTO(K entity, T t) {
    if (Objects.isNull(entity)) {
      return null;
    }
    BeanUtils.copyProperties(entity, t);

    return t;
  }

  public List<T> toDTO(List<K> klist, Class<T> t) {
    List<T> tlist = new ArrayList<T>();
    T tnew = null;
    try {
      for (K k : klist) {
        tnew = t.newInstance();
        tlist.add(toDTO(k, tnew));
      }
    } catch (Exception e) {
      log.error("转换dto实例化:" + t.getName() + " 失败");
    }
    return tlist;
  }
}