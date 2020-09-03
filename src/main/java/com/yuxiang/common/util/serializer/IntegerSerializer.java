
package com.yuxiang.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2019/11/5 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */

public class IntegerSerializer extends JsonSerializer<Integer> {
  @Override
  public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    String dateParser;
    try {
      if (0 == value) {
        dateParser = "";
      } else {
        dateParser = value.toString();
      }
    } catch (Exception e) {
      dateParser = "**";
    }
    gen.writeString(dateParser);

  }
}
