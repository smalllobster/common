
package com.yuxiang.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 功能说明: JAVA版本的自动生成有规则的订单号(或编号) 生成的格式是: 200908010001 前面几位为当前的日期,后面五位为系统自增长类型的编号
 * 功能原理: 1.获取当前日期格式化值; 2.读取文件,上次编号的值+1最为当前此次编号的值 (新的一天会重新从1开始编号)
 * 开发人员: kjp
 * 开发时间: 2018/11/21 14:00 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class OrderNoUtil {
  protected final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

  static OrderNoUtil orderNoUtil = new OrderNoUtil();

  public static OrderNoUtil getInstance() {
    if (orderNoUtil == null) {
      orderNoUtil = new OrderNoUtil();
      return orderNoUtil;
    } else {
      return orderNoUtil;
    }
  }

  /**
   * 产生唯一 的序列号。
   *
   * @return
   */
  public static String getSerialNumber() {
    int hashCode = UUID.randomUUID().toString().hashCode();
    if (hashCode < 0) {
      hashCode = -hashCode;
    }
    return sdf.format(new Date()).substring(2, 8) + String.format("%010d", hashCode);
  }
}
