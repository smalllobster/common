
package com.yuxiang.common.util.file.validator;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * 功能说明: 文件头安全性校验
 * 开发人员: kjp
 * 开发时间: 2018/11/26 <br>
 * 功能描述: 文件头安全性校验<br>
 */
public class FileValidator {

  /**
   * 校验文件头魔数
   *
   * @param file
   * @return
   */
  public static final boolean validate(MultipartFile file) {
    try {
      byte[] bytes = Arrays.copyOfRange(file.getBytes(), 0, 28);
      String magicNum = bytes2Hex(bytes);
//      String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
      for (FileType type : FileType.values()) {
        if (magicNum.toUpperCase().startsWith(type.getValue())) {
          return true;
        }
      }
      return false;
    } catch (Exception e) {
      return false;
    }
//    return true;
  }

  private static final String bytes2Hex(byte[] b) {
    StringBuilder stringBuilder = new StringBuilder();
    if (b == null || b.length <= 0) {
      return null;
    }
    for (int i = 0; i < b.length; i++) {
      int v = b[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }

}
