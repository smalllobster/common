
package com.yuxiang.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yuxiang.common.util.helper.DateHelper;

import java.io.IOException;
import java.util.Date;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/25 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class DateSerializer extends JsonSerializer<Date> {

  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    String dateParser;
    try {
      dateParser = DateHelper.formatDate(date);
    } catch (Exception e) {
      dateParser = "**";
    }
    jsonGenerator.writeString(dateParser);
  }
}
