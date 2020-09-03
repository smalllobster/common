
package com.yuxiang.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 功能说明:保留零未big<br>
 * 开发人员: kjp
 * 开发时间: 2018年7月22日 <br>
 * 功能描述: <br>
 */
public class BigDecimalZeroSerializer  extends JsonSerializer<BigDecimal> {


  @Override
  public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    String dateParser;
    try {
      dateParser = value.setScale(0).toPlainString();
    } catch (Exception e) {
      dateParser = "**";
    }
    gen.writeString(dateParser);
  }

}
