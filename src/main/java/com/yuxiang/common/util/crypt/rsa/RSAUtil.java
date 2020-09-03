
package com.yuxiang.common.util.crypt.rsa;

import com.yuxiang.common.util.crypt.cord.BASE64Decoder;
import com.yuxiang.common.util.crypt.cord.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 功能说明: 开发人员: kjp
 * 开发时间: 2018年6月11日
 * 功能描述: RSA加解密工具类
 */
public class RSAUtil {
  public static final int MAX_ENCRYPT_BLOCK = 117;
  public static final int MAX_DECRYPT_BLOCK = 128;
  public static final String KEY_ALGORTHM = "RSA";//
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
  private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

  /**
   * 用私钥对信息生成数字签名
   */
  @SuppressWarnings("restriction")
  public static String sign(String data, String privateKey) {
    try {
      byte[] dataBytes = new BASE64Decoder().decodeBuffer(data);
      byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
      PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
      KeyFactory key = KeyFactory.getInstance(KEY_ALGORTHM);
      PrivateKey privateKey2 = key.generatePrivate(pkcs8EncodedKeySpec);
      // 用私钥对信息生成数字签名
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initSign(privateKey2);
      signature.update(dataBytes);
      return new BASE64Encoder().encode(signature.sign());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String buildSignStr(TreeMap<String, Object> data) {
    Iterator<Entry<String, Object>> it = data.entrySet().iterator();
    StringBuffer sb = new StringBuffer();
    while (it.hasNext()) {
      Entry<String, Object> entry = it.next();
      sb.append(entry.getKey()).append("=").append(entry.getValue());
      if (!data.lastKey().equalsIgnoreCase(entry.getKey())) {
        sb.append("&");
      }
    }
    return sb.toString();
  }

  public static String buildSignStr(HashMap<String, String> data) {
    Iterator<Entry<String, String>> it = data.entrySet().iterator();
    StringBuffer sb = new StringBuffer();
    while (it.hasNext()) {
      Entry<String, String> entry = it.next();
      sb.append(entry.getKey()).append("=").append(entry.getValue());
      sb.append("&");
    }
    return sb.toString();
  }

  public static boolean verify(TreeMap<String, Object> data, String sign, String publicKey) {
    return verify(buildSignStr(data), publicKey, sign);
  }

  /**
   * 校验数字签名
   */
  private static boolean verify(String data, String publicKey, String sign) {
    try {
      byte[] dataBytes = new BASE64Decoder().decodeBuffer(data);
      byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
      X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
      KeyFactory key = KeyFactory.getInstance(KEY_ALGORTHM);
      PublicKey publicKey2 = key.generatePublic(x509EncodedKeySpec);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initVerify(publicKey2);
      signature.update(dataBytes);

      return signature.verify(new BASE64Decoder().decodeBuffer(sign));
    } catch (Exception e) {
      LOGGER.error("签名验证失败" + e.getMessage());
    }

    return false;
  }

  public static void main(String[] args) {
    getKeyPairs();
  }

  /**
   * 产生密钥对
   */
  public static void getKeyPairs() {
    KeyPairGenerator keyPairGenerator;
    try {
      keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
      keyPairGenerator.initialize(1024);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      // 公钥
      RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
      System.out.println("公钥：" + new BASE64Encoder().encode(publicKey.getEncoded()));
      System.out.println("-----------------------------------------------------------------------");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      // 私钥
      RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
      System.out.println("私钥：" + new BASE64Encoder().encode(privateKey.getEncoded()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 将 进行 BASE64 编码
   *
   * @param s
   * @return
   */
  public static String getBASE64(String s) {
    if (s == null)
      return null;
    final Base64 base64 = new Base64();
    return base64.encodeToString(s.getBytes());
  }

  /**
   * 将 BASE64 编码的字符串 s 进行解码
   *
   * @param str
   * @return
   */
  public static String getFromBASE64(String str) {
    final Base64 base64 = new Base64();
    if (str == null)
      return null;
    try {
      return new String(base64.decode(str), "UTF-8");
    } catch (UnsupportedEncodingException e) {

    }
    return "";
  }

}
