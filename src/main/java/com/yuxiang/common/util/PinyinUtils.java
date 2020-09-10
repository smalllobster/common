
package com.yuxiang.common.util;

import com.yuxiang.common.exception.BaseException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能说明: 实现汉字转拼音
 * 开发人员: kjp
 * 开发时间: 2018/9/30 11:26 <br>
 * 功能描述: 实现汉字转拼音<br>
 */
public class PinyinUtils {

  public static void main(String[] args) {
    System.err.println(toPinyin(("黄少华")));
  }

  public static String toPinyin(String chineseStr) {
    String pinyinName = "";
    if (StringUtils.isNotBlank(chineseStr)) {
      if (isChinese(chineseStr)) {
        char[] nameChar = chineseStr.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
          try {
            pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
          } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
            return null;
          }
        }
        return pinyinName;
      } else {
        throw new BaseException("您输入的【" + chineseStr + "】不是汉字");
      }
    }
    return null;
  }

  private static boolean isChinese(String str) {
    String regEx = "[\u4e00-\u9fa5]";
    Pattern pat = Pattern.compile(regEx);
    Matcher matcher = pat.matcher(str);
    boolean flg = false;
    if (matcher.find()) {
        flg = true;
    }
    return flg;
  }

}
