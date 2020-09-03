package com.yuxiang.common.util.crypt.des.old;

import java.security.GeneralSecurityException;

/**
 * 加密解密Text类型的数据
 *
 * @author kjp
 */
public interface TextCipher {

  String SALT = "RegTech!";

  /**
   * 初始化
   *
   * @param salt
   */
  void init(String salt);

  /**
   * 加密信息
   *
   * @param value
   * @return
   * @throws GeneralSecurityException
   */
  String encrypt(String value) throws GeneralSecurityException;

  /**
   * 解密信息
   *
   * @param value
   * @return
   * @throws GeneralSecurityException
   */
  String decrypt(String value) throws GeneralSecurityException;
}
