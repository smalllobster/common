
package com.yuxiang.common.util.validator;

import com.yuxiang.common.enums.RegexEnum;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能说明: 正则表达式校验
 * 开发人员: kjp
 * 开发时间: 2018/6/22 <br>
 * 功能描述: 正则表达式校验<br>
 */
public final class RegexValidUtil {

  /**
   * 校验登录用户名
   *
   * @param loginName
   * @return
   */
  public static final boolean regexLoginName(String loginName) {
    return matcherByExp(objectToStr(loginName), RegexEnum.LOGIN_NAME.getExp());
  }

  /**
   * 校验登录密码
   *
   * @param password
   * @return
   */
  public static final boolean regexPassword(String password) {
    return matcherByExp(objectToStr(password), RegexEnum.PASSWORD.getExp());
  }

  /**
   * 校验交易密码
   *
   * @param payPassword
   * @return
   */
  public static final boolean regexPayPassword(String payPassword) {
    return matcherByExp(objectToStr(payPassword), RegexEnum.PAY_PASSWORD.getExp());
  }

  /**
   * 校验手机号码
   *
   * @param mobilePhone
   * @return
   */
  public static final boolean regexMobilePhone(String mobilePhone) {
    return matcherByExp(objectToStr(mobilePhone), RegexEnum.MOBILE_PHONE.getExp());
  }

  /**
   * 校验固定电话
   *
   * @param telPhone
   * @return
   */
  public static final boolean regexTelPhone(String telPhone) {
    telPhone = objectToStr(telPhone);
    if (telPhone.length() > 9) {
      return matcherByExp(telPhone, RegexEnum.TEL_PHONE_AREA.getExp());
    } else {
      return matcherByExp(telPhone, RegexEnum.TEL_PHONE.getExp());
    }
  }

  /**
   * 校验银行卡号
   *
   * @param bankCard
   * @return
   */
  public static final boolean regexBankCard(String bankCard) {
    return matcherByExp(objectToStr(bankCard), RegexEnum.BANK_CARD.getExp());
  }

  /**
   * 校验身份证号
   *
   * @param cardId
   * @return
   */
  public static final boolean regexCardId(String cardId) {
    return matcherByExp(objectToStr(cardId), RegexEnum.CARD_ID.getExp());
  }

  /**
   * 校验姓名
   *
   * @param realName
   * @return
   */
  public static final boolean regexRealName(String realName) {
    return matcherByExp(objectToStr(realName), RegexEnum.REAL_NAME.getExp());
  }

  /**
   * 校验邮箱格式
   *
   * @param email
   * @return
   */
  public static final boolean regexEmail(String email) {
    return matcherByExp(objectToStr(email), RegexEnum.EMAIL.getExp());
  }

  /**
   * 校验数字
   *
   * @param number
   * @return
   */
  public static final boolean regexNumber(String number) {
    return matcherByExp(objectToStr(number), RegexEnum.NUMBER.getExp());
  }

  /**
   * 校验整数
   *
   * @param integer
   * @return
   */
  public static final boolean regexInteger(String integer) {
    return matcherByExp(objectToStr(integer), RegexEnum.INTEGER.getExp());
  }

  /**
   * 校验小数（两位正数）
   *
   * @param decimal
   * @return
   */
  public static final boolean regexDecimal(String decimal) {
    return matcherByExp(objectToStr(decimal), RegexEnum.DECIMAL.getExp());
  }

  /**
   * 校验cron表达式
   *
   * @param cronStr
   * @return
   */
  public static final boolean regexCron(String cronStr) {
    return matcherByExp(objectToStr(cronStr), RegexEnum.CRON.getExp());
  }

  /**
   * 校验统一社会信用代码
   *
   * @param businessCode
   * @return
   */
  public static final boolean regexBusinessCode(String businessCode) {
    if ((businessCode.equals("")) || businessCode.length() != 18) {
      return false;
    }
    String regex = "^([0-9ABCDEFGHJKLMNPQRTUWXY]{2})([0-9]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{10})$";
    if (!businessCode.matches(regex)) {
      return false;
    }

    String baseCode = "0123456789ABCDEFGHJKLMNPQRTUWXY";
    char[] baseCodeArray = baseCode.toCharArray();
    Map<Character, Integer> codes = new HashMap<>();
    for (int i = 0; i < baseCode.length(); i++) {
      codes.put(baseCodeArray[i], i);
    }
    char[] businessCodeArray = businessCode.toCharArray();
    Character check = businessCodeArray[17];
    if (baseCode.indexOf(check) == -1) {
      return false;
    }
    int[] wi = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
    int sum = 0;
    for (int i = 0; i < 17; i++) {
      Character key = businessCodeArray[i];
      if (baseCode.indexOf(key) == -1) {
        return false;
      }
      sum += (codes.get(key) * wi[i]);
    }
    int value = 31 - sum % 31;
    if (value == 31) {
      value = 0;
    }
    return value == codes.get(check);
  }

  /**
   * 正则匹配
   *
   * @param str
   * @param exp
   * @return
   */
  public static final boolean matcherByExp(String str, String exp) {
    if (StringUtils.isEmpty(str)) {
      return false;
    }
    Pattern regex = Pattern.compile(exp);
    Matcher matcher = regex.matcher(str);
    return matcher.matches();
  }

  /**
   * 对象转为字符串
   *
   * @param object
   * @return
   */
  public static final String objectToStr(Object object) {
    String str;
    if (object == null) {
      return "";
    }
    if (object instanceof String) {
      str = (String) object;
    } else {
      str = object.toString();
    }
    return str.trim();
  }

}
