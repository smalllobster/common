
package com.yuxiang.common.util.crypt.des;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Locale;

/**
 * 功能说明: DES加密、解密
 * 开发人员: kjp
 * 开发时间: 2018/7/19 17:38 <br>
 * 功能描述: 用于加密、解密br>
 */
public class DESCryptorUtil {

  /**
   * 加密
   *
   * @param str 原字符串
   * @param key 加密密钥
   * @return    加密后的字符串
   * @throws Exception
   * @since 0.0.1
   */
  public static String getEncString(String str, String key) throws Exception {
    return byteArrToHexStr(encrypt(str.getBytes(), key));
  }
public static void main(String[] args) {
  try {
    System.out.println(getEncString("111111", "abcdefgh"));
   // System.out.println(getDecString("L0yIIjy56yM=", "abcdefgh"));
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
}
  /**
   * 解密
   *
   * @param str
   *            原字符串
   * @param key
   *            加密密钥
   * @return 解密后的字符串
   * @throws Exception
   * @since 0.0.1
   */
  public static String getDecString(String str, String key) throws Exception {
    return new String(decrypt(hexStrToByteArr(str), key));
  }

  /**
   * byte[]数组转换为16进制的字符串
   *
   * @param data 要转换的字节数组
   * @return 转换后的结果
   */
  public static final String byteArrToHexStr(byte[] data) {
    StringBuilder sb = new StringBuilder(data.length * 2);
    for (byte b : data) {
      int v = b & 0xff;
      if (v < 16) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(v));
    }
    return sb.toString().toUpperCase(Locale.getDefault());
  }

  /**
   * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
   * 互为可逆的转换过程
   *
   * @param strIn
   *            需要转换的字符串
   * @return 转换后的byte数组
   * @throws Exception
   *             本方法不处理任何异常，所有异常全部抛出
   * @since 0.0.1
   */
  public static byte[] hexStrToByteArr(String strIn) throws Exception {
    byte[] arrB = strIn.getBytes();
    int iLen = arrB.length;

    // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
    byte[] arrOut = new byte[iLen / 2];
    for (int i = 0; i < iLen; i = i + 2) {
      String strTmp = new String(arrB, i, 2);
      arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
    }
    return arrOut;
  }

  /**
   * 功能描述：加密字节数组
   *
   * @param arrB 需加密的字节数组
   * @return 加密后的字节数组
   * @throws Exception
   */
  private static byte[] encrypt(byte[] arrB, String strKey) throws Exception {
    Cipher encryptCipher = Cipher.getInstance("DES");
    Key key = getKey(strKey.getBytes());
    encryptCipher.init(Cipher.ENCRYPT_MODE, key);
    return encryptCipher.doFinal(arrB);
  }

  /**
   * 功能描述：解密字节数组
   *
   * @param arrB
   *            要解密的数组
   * @param strKey
   *            密钥
   * @return 解密后的字节数组
   * @throws Exception
   */
  private static byte[] decrypt(byte[] arrB, String strKey) throws Exception {
    Cipher decryptCipher = Cipher.getInstance("DES");
    Key key = getKey(strKey.getBytes());
    decryptCipher.init(Cipher.DECRYPT_MODE, key);
    return decryptCipher.doFinal(arrB);
  }

  /**
   * 功能描述：从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
   *
   * @param arrBTmp 构成该字符串的字节数组
   * @return 生成的密钥
   * @throws Exception
   */
  public static Key getKey(byte[] arrBTmp) throws Exception {
    // 创建一个空的8位字节数组（默认值为0）
    byte[] arrB = new byte[8];
    // 将原始字节数组转换为8位
    for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
      arrB[i] = arrBTmp[i];
    }
    // 生成密钥
    Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
    return key;
  }

}
