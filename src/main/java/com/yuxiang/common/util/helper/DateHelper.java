package com.yuxiang.common.util.helper;

import com.yuxiang.common.enums.DateEnum;
import com.yuxiang.common.enums.TimeUnitEnum;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 * 功能说明: 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 开发人员: kjp
 * 开发时间: 10:31 <br>
 * 功能描述: <br>
 */
@SuppressWarnings("AlibabaAvoidNewDateGetTime")
public class DateHelper extends DateUtils {

  public static final String DEFALT_DATE_STR = "--";

  /**
   * 日期格式
   */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
  /**
   * 日期时间格式
   */
  public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  /**
   * 日期格式
   */
  private static final String[] PARSE_PATTERNS = {
      "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
      "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
      "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
  };

  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd）
   *
   * @return the date
   */
  public static String getDate() {
    return getDate(DEFAULT_DATE_FORMAT);
  }

  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   *
   * @param pattern the pattern
   * @return the date
   */
  public static String getDate(String pattern) {
    return DateFormatUtils.format(new Date(), pattern);
  }

  /**
   * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   *
   * @param date    the date
   * @param pattern the pattern
   * @return the string
   */
  public static String formatDate(Date date, Object... pattern) {
    String formatDate;
    if (pattern != null && pattern.length > 0) {
      formatDate = DateFormatUtils.format(date, pattern[0].toString());
    } else {
      formatDate = DateFormatUtils.format(date, DEFAULT_DATE_FORMAT);
    }
    return formatDate;
  }

  /**
   * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
   *
   * @param date the date
   * @return the string
   */
  public static String formatDateTime(Date date) {
    return formatDate(date, DEFAULT_DATETIME_FORMAT);
  }

  /**
   * 格式化时间
   * @param date
   * @return
   */
  public static String formatTime(Date date) {
    return formatDate(date, "HH:mm:ss");
  }
  /**
   * 得到当前时间字符串 格式（HH:mm:ss）
   *
   * @return the time
   */
  public static String getTime() {
    return formatDate(new Date(), "HH:mm:ss");
  }

  /**
   * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
   *
   * @return the date time
   */
  public static String getDateTime() {
    return formatDate(new Date(), DEFAULT_DATETIME_FORMAT);
  }

  /**
   * 得到当前年份字符串 格式（yyyy）
   *
   * @return the year
   */
  public static String getYear() {
    return formatDate(new Date(), "yyyy");
  }

  /**
   * 得到当前年份字符串 格式（yyyy）
   *
   * @return the year
   */
  public static String getYear(Date date) {
    return formatDate(date, "yyyy");
  }

  /**
   * 得到当前月份字符串 格式（MM）
   *
   * @return the month
   */
  public static String getMonth() {
    return formatDate(new Date(), "MM");
  }

  /**
   * 得到当前月份字符串 格式（MM）
   *
   * @return the month
   */
  public static String getMonth(Date date) {
    return formatDate(date, "MM");
  }

  /**
   * 得到当天字符串 格式（dd）
   *
   * @return the day
   */
  public static String getDay() {
    return formatDate(new Date(), "dd");
  }

  /**
   * 得到当天字符串 格式（dd）
   *
   * @return the day
   */
  public static String getDay(Date date) {
    return formatDate(date, "dd");
  }

  /**
   * 得到当前星期字符串 格式（E）星期几
   *
   * @return the week
   */
  public static String getWeek() {
    return formatDate(new Date(), "E");
  }

  /**
   * 日期型字符串转化为日期 格式
   * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
   * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
   * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
   *
   * @param str the str
   * @return the date
   */
  public static Date parseDate(Object str) {
    if (str == null) {
      return null;
    }
    try {
      return parseDate(str.toString(), PARSE_PATTERNS);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 获取过去的天数
   *
   * @param date 对比日期
   * @return long long
   */
  public static long pastDays(Date date) {
    long t = new Date().getTime() - date.getTime();
    return t / (24 * 60 * 60 * 1000);
  }

  /**
   * 获取过去的小时
   *
   * @param date 对比日期
   * @return long long
   */
  public static long pastHour(Date date) {
    long t = new Date().getTime() - date.getTime();
    return t / (60 * 60 * 1000);
  }

  public static void main(String[] args) {
    Date time = DateHelper.getDate("2019-09-23 21:06:00", "yyyy-MM-dd HH:mm:ss");

    // 计算 创建时间至服务器时间的时间间隔（分钟）
    long happenTime = DateHelper.pastMinutes(time);
    if (happenTime <= 10) {
      System.out.println("刚刚");
    } else if (happenTime < 60) {
      System.out.println(happenTime + "分钟前");
    } else if (happenTime < (60 * 24)) {
      System.out.println(happenTime / 60 + "小时前");
    } else if (happenTime < (60 * 24 * 99)) {
      System.out.println(happenTime / (60 * 24) + "天前");
    } else {
//      message.setCreateTimeStr(DateHelper.getYYYY_MM_DD_HH_MM_SS(message.getCreateTime()));
    }

  }

  /**
   * 获取过去的分钟
   *
   * @param date 对比日期
   * @return long long
   */
  public static long pastMinutes(Date date) {
    long t = new Date().getTime() - date.getTime();
    return t / (60 * 1000);
  }

  /**
   * 转换为时间（天,时:分:秒.毫秒）
   *
   * @param timeMillis 毫秒数
   * @return 天, 时:分:秒.毫秒
   */
  public static String formatDateTime(long timeMillis) {
    long day = timeMillis / (24 * 60 * 60 * 1000);
    long hour = timeMillis / (60 * 60 * 1000) - day * 24;
    long min = timeMillis / (60 * 1000) - day * 24 * 60 - hour * 60;
    long s = timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
    long sss = timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000;
    return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
  }

  /**
   * 获取两个日期之间的天数
   *
   * @param before 开始日期
   * @param after  结束日期
   * @return 天数 distance of two date
   */
  public static double getDistanceOfTwoDate(Date before, Date after) {
    long beforeTime = before.getTime();
    long afterTime = after.getTime();
    return (afterTime - beforeTime) / (double) (1000 * 60 * 60 * 24);
  }

  /**
   * 获取东八区当前时间
   *
   * @return Date est 8 date
   */
  public static Date getEst8Date() {
    TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
    dateFormat.setTimeZone(tz);
    return parseDate(dateFormat.format(date));
  }

  /**
   * 从date开始往后推迟interval间隔时间，间隔类型rollType
   *
   * @param date
   * @param interval
   * @param rollType
   * @return
   */
  public static Date rollDate(Date date, int interval, int rollType) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(rollType, interval);
    return cal.getTime();
  }

  /**
   * 获取MMDD
   *
   * @param date
   * @return
   */
  public static String getMM_DD(Date date) {
    if (Objects.nonNull(date)) {
      return getDateStr(date, DateEnum.MM_DD.getFormatCode());
    } else {
      return DEFALT_DATE_STR;
    }
  }

  /**
   * 获取yyyyMM
   *
   * @param date
   * @return
   */
  public static String getYYYYMM(Date date) {
    if (Objects.nonNull(date)) {
      return getDateStr(date, DateEnum.YYYYMM.getFormatCode());
    } else {
      return DEFALT_DATE_STR;
    }
  }

  /**
   * 获取yyyyMMdd
   *
   * @param date
   * @return
   */
  public static String getYYYYMMDD(Date date) {
    if (Objects.nonNull(date)) {
      return getDateStr(date, DateEnum.YYYYMMDD.getFormatCode());
    } else {
      return DEFALT_DATE_STR;
    }
  }

  /**
   * 获取yyyyMMdd
   *
   * @param strDate 格式要求yyyyMMdd
   * @return
   */
  public static Date getYYYYMMDD(String strDate) {
    return getDate(strDate, DateEnum.YYYYMMDD.getFormatCode());
  }

  /**
   * 获取yyyy-MM-dd
   *
   * @param date
   * @return
   */
  public static String getYYYY_MM_DD(Date date) {
    if (Objects.nonNull(date)) {
      return getDateStr(date, DateEnum.YYYY_MM_DD.getFormatCode());
    } else {
      return DEFALT_DATE_STR;
    }
  }

  /**
   * 获取yyyy-MM-dd
   *
   * @param strDate 格式要求yyyy-MM-dd
   * @return
   */
  public static Date getYYYY_MM_DD(String strDate) {
    return getDate(strDate, DateEnum.YYYY_MM_DD.getFormatCode());
  }

  /**
   * 获取yyyy-MM-dd
   *
   * @param localDate
   * @return
   */
  public static String getYYYY_MM_DD(LocalDate localDate) {
    return getLocalDateStr(localDate, DateEnum.YYYY_MM_DD.getFormatCode());
  }

  /**
   * 获取yyyyMMddHHmmss
   *
   * @param date
   * @return
   */
  public static String getYYYYMMDDHHMMSS(Date date) {
    if (Objects.nonNull(date)) {
      return getDateStr(date, DateEnum.YYYYMMDDHHMMSS.getFormatCode());
    } else {
      return DEFALT_DATE_STR;
    }
  }
  /**
   * 获取yyyy-MM-dd HH:mm:ss
   *
   * @param strDate 格式要求yyyy-MM-dd
   * @return
   */
  public static Date getYYYY_MM_DD_HH_MM_SS(String strDate) {
    return getDate(strDate, DateEnum.YYYY_MM_DD_HH_MM_SS.getFormatCode());
  }
  /**
   * 获取yyyy-MM-dd HH:mm:ss
   *
   * @param date
   * @return
   */
  public static String getYYYY_MM_DD_HH_MM_SS(Date date) {
    return getDateStr(date, DateEnum.YYYY_MM_DD_HH_MM_SS.getFormatCode());
  }

  /**
   * 获取yyyy-MM-dd hh:mm:ss.SSS
   *
   * @param date
   * @return
   */
  public static String getYYYY_MM_DD_HH_MM_SS_SSS(Date date) {
    return getDateStr(date, DateEnum.YYYY_MM_DD_HH_MM_SS_SSS.getFormatCode());
  }

  /**
   * 获得当前日期
   *
   * @return
   */
  public static Date getNow() {
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();
    return currDate;
  }

  /**
   * 字符串转换为日期 格式自定义
   *
   * @param strDate
   * @param f
   * @return
   */
  public static Date getDate(String strDate, String f) {
    SimpleDateFormat formatter = new SimpleDateFormat(f);
    ParsePosition pos = new ParsePosition(0);
    Date strToDate = formatter.parse(strDate, pos);
    return strToDate;
  }

  /**
   * 日期转换为字符串 格式自定义
   *
   * @param date
   * @param f
   * @return
   */
  public static String getDateStr(Date date, String f) {
    SimpleDateFormat format = new SimpleDateFormat(f);
    String str = format.format(date);
    return str;
  }

  /**
   * 将时间戳转换为Date
   *
   * @param timestamp
   * @return
   */
  public static Date getDateByTimestamp(long timestamp) {
    return new Date(timestamp * 1000);
  }

  /**
   * 将时间戳转换为Date
   *
   * @param timestamp
   * @return
   */
  public static Date getDateByTimestamp(String timestamp) {
    long times = Long.parseLong(timestamp);
    return new Date(times);
  }

  /**
   * 将Date转换为时间戳
   *
   * @param dateStr
   * @return
   */
  public static String getTimestampByDate(String dateStr) {
    SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    long time = format.parse(dateStr, new ParsePosition(0)).getTime() / 1000;
    return String.valueOf(time);
  }

  /**
   * 日期转换为字符串 格式自定义
   *
   * @param localDate
   * @param f
   * @return
   */
  public static String getLocalDateStr(LocalDate localDate, String f) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(f);
    return dateTimeFormatter.format(localDate);
  }

  /**
   * 计算时间前/后
   *
   * @param unit   时间单位
   * @param number 加/减？时间单位
   * @param date   时间
   * @return
   */
  public static Date dateAdd(TimeUnitEnum unit, int number, Date date) {
    Calendar now = Calendar.getInstance();
    now.setTime(date);
    switch (unit) {
      case YEAR:
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + number);
        break;
      case MONTH:
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + number);
        break;
      case DAY:
        now.set(Calendar.DATE, now.get(Calendar.DATE) + number);
        break;
      case HOUR:
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + number);
        break;
      case MINUTE:
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + number);
        break;
      case SECOND:
        now.set(Calendar.SECOND, now.get(Calendar.SECOND) + number);
        break;
      default:
        throw new IllegalArgumentException("时间单位不正确！");
    }
    return now.getTime();
  }

  /**
   * 计算两个日期之间相差的天数
   *
   * @param startDate
   * @param endDate
   * @return
   */
  public static Integer daysBetween(Date startDate, Date endDate) {
    DateFormat sdf = new SimpleDateFormat(DateEnum.YYYYMMDD.getFormatCode());
    Calendar cal = Calendar.getInstance();
    try {
      Date d1 = sdf.parse(DateHelper.getYYYYMMDD(startDate));
      Date d2 = sdf.parse(DateHelper.getYYYYMMDD(endDate));
      cal.setTime(d1);
      long time1 = cal.getTimeInMillis();
      cal.setTime(d2);
      long time2 = cal.getTimeInMillis();
      return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 校验日期格式
   *
   * @param dateStr
   * @param f
   * @return
   */
  public static boolean isValidDate(String dateStr, String f) {
    boolean convertSuccess = true;

    SimpleDateFormat format = new SimpleDateFormat(f);
    try {
      format.setLenient(false);
      format.parse(dateStr);
    } catch (ParseException e) {
      convertSuccess = false;
    }
    return convertSuccess;
  }

  /**
   * 判断选择的日期是否是本周
   *
   * @param date
   * @return
   */
  public static boolean isThisWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    int currentYear =  calendar.get(Calendar.YEAR);
    int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
    calendar.setTime(date);
    int paramYear=calendar.get(Calendar.YEAR);
    if (paramYear == currentYear) {
      int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
      if (paramWeek == currentWeek) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断选择的日期是否是今日或本月
   *
   * @param date
   * @param pattern 今日判断(yyyy-MM-dd),本月判断(yyyy-MM)
   * @return
   */
  public static boolean isThisTime(Date date, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    String param = sdf.format(date);//参数时间
    String now = sdf.format(new Date());//当前时间
    if (param.equals(now)) {
      return true;
    }
    return false;
  }

  /**
   * 得到距离24点剩下的时间戳
   *
   * @return
   */
  public static Long getLeftTime() {
    Date currentDate = new Date();
    LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
        ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
        .withSecond(0).withNano(0);
    LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
        ZoneId.systemDefault());
    long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
    return seconds;
  }
}
