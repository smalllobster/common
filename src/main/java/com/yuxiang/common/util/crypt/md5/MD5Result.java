
package com.yuxiang.common.util.crypt.md5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * 功能说明: md5加密结果
 * 开发人员: kjp
 * 开发时间: 2019/1/10 <br>
 * 功能描述: md5加密结果<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MD5Result {

  public MD5Result(String salt) {
    this.salt = salt;
  }

  /**
   * 盐值
   */
  private String salt;
  /**
   * md5结果值
   */
  private String md5;

  public static MD5Result newInstance() {
    Random r = new Random();
    StringBuilder sb = new StringBuilder(16)
        .append(r.nextInt(99999999)).append(r.nextInt(99999999));
    for (int i = 0, len = 16 - sb.length(); i < len; i++) {
      sb.append("0");
    }
    return new MD5Result(sb.toString());
  }
}
