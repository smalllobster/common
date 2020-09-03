
package com.yuxiang.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yuxiang.common.constant.Constant;
import com.yuxiang.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 功能说明: dict序列化
 * 开发人员: kjp
 * 开发时间: 2018/7/3 <br>
 * 功能描述: dict序列化<br>
 */
@Slf4j
public class DictSerializer extends JsonSerializer<Object> {

  //private RedisRepository redisRepository;

  /**
   * 系统配置服务，获取字典数据的方法
   */
  private Method getDictMethod;
  /**
   * 字典数据对象名称获取方法
   */
  private Method getDictNameMethod;
  /**
   * 系统配置服务
   */
  private Object globalConfig;

  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    /*if (Objects.isNull(redisRepository)) {
      redisRepository = (RedisRepository) SpringUtil.getBean("redisRepository");
    }*/
    if (Objects.isNull(globalConfig)) {
      globalConfig = SpringUtil.getBean("globalConfig");
    }
    JsonStreamContext context = gen.getOutputContext();
    Object instance = context.getCurrentValue();
    Class clazz = instance.getClass();
    String key = clazz.getName() + Constant.CLASS_FIELD_SPLIT_SYMBOL + context.getCurrentName();
    // 获取redis中字段的对应配置
    //String config = redisRepository.getHashValues(Constant.DICT_FIELD_MAP, key) + "";
    // 获取对应的dict对象
    //String[] configValue = config.split("\\" + Constant.STRING_SPLIT_DOLLAR);

    /*try {
      if (Objects.isNull(getDictMethod)) {
        getDictMethod = globalConfig.getClass().getMethod("getDictDTO", String.class, String.class);
      }
      Object dictDTO = getDictMethod.invoke(globalConfig, configValue[0], value + "");
      if (Objects.isNull(dictDTO)) {
        gen.writeString(value + "");
        return;
      }
      try {
        if (Objects.isNull(getDictNameMethod)) {
          getDictNameMethod = dictDTO.getClass().getMethod("getDictName");
        }
        Method setMethod = clazz.getMethod(new StringBuilder("set")
            .append(configValue[1].toUpperCase().substring(0, 1).toUpperCase())
            .append(configValue[1].substring(1)).toString(), String.class);
        setMethod.invoke(instance, getDictNameMethod.invoke(dictDTO));
      } catch (Exception e) {
        log.error("dict数据转换失败");
      }
    } catch (NoSuchMethodException e) {
      log.error("配置服务请求错误", e);
      gen.writeString(value + "");
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }*/

    gen.writeString(value + "");
  }
}
