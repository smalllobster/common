
package com.yuxiang.common.util.crypt.des.old;

import com.yuxiang.common.exception.YXException;
import com.yuxiang.common.util.helper.StringHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/23 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Slf4j
public class DecryptEncryptUtil {

  /**
   * 参数解密
   *
   * @param encodeString
   * @return
   */
  public static Map<String, Object> DESDecryptParams(String encodeString) {
    Map<String, Object> req = new HashMap<>();
    String decryStr = DESDecrypt(encodeString);
    String[] params = decryStr.split("&");
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        String[] param = params[i].split("=");
        if (param != null && param.length == 2) {
          req.put(param[0], param[1]);
        }
      }
    }
    return req;
  }

  /**
   * 解密
   *
   * @param s
   * @return
   */
  public static String DESDecrypt(String s) {
    if (StringHelper.isBlank(s)) {
      return s;
    }
    TextCipher textCipher = new DESTextCipher();
    textCipher.init(TextCipher.SALT);
    try {
      return textCipher.decrypt(s.trim());
    } catch (Exception e) {
      throw new YXException("解密信息出错");
    }
  }

  /**
   * 加密
   *
   * @param s
   * @return
   */
  public static String DESEncrypt(String s) {
    if (StringHelper.isBlank(s)) {
      return s;
    }
    TextCipher textCipher = new DESTextCipher();
    textCipher.init(TextCipher.SALT);
    try {
      return textCipher.encrypt(s.trim());
    } catch (Exception e) {
      throw new YXException("加密信息出错");
    }
  }

  /**
   * 解密
   *
   * @param s
   * @return
   */
  public static String DESDecryptHPJR(String s) {
    if (StringHelper.isBlank(s)) {
      return s;
    }
    TextCipher textCipher = new DESTextCipher();
    textCipher.init("CreditCloudRock");
    try {
      return textCipher.decrypt(s.trim());
    } catch (Exception e) {
      throw new YXException("解密信息出错");
    }
  }

  /**
   * 加密
   *
   * @param s
   * @return
   */
  public static String DESEncryptHPJR(String s) {
    if (StringHelper.isBlank(s)) {
      return s;
    }
    TextCipher textCipher = new DESTextCipher();
    textCipher.init("CreditCloudRock");
    try {
      return textCipher.encrypt(s.trim());
    } catch (Exception e) {
      throw new YXException("加密信息出错");
    }
  }

}
