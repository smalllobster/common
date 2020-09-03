
package com.yuxiang.common.util;

import com.alibaba.fastjson.JSONObject;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: 获取系统和浏览器信息
 * 开发人员: kjp
 * 开发时间: 2018/6/28 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Slf4j
public class RequestInfoUtil {

  public static final String BROWSER_KEY = "browser";

  public static final String OS_KEY = "os";

  /**
   * 获取真实用户ip
   *
   * @return
   */
  public static String getIp() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String ip = null;

    //X-Forwarded-For：Squid 服务代理
    String ipAddresses = request.getHeader("X-Forwarded-For");

    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
      //Proxy-Client-IP：apache 服务代理
      ipAddresses = request.getHeader("Proxy-Client-IP");
    }

    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
      //WL-Proxy-Client-IP：weblogic 服务代理
      ipAddresses = request.getHeader("WL-Proxy-Client-IP");
    }

    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
      //HTTP_CLIENT_IP：有些代理服务器
      ipAddresses = request.getHeader("HTTP_CLIENT_IP");
    }

    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
      //X-Real-IP：nginx服务代理
      ipAddresses = request.getHeader("X-Real-IP");
    }

    //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
    if (ipAddresses != null && ipAddresses.length() != 0) {
      ip = ipAddresses.split(",")[0];
    }

    //还是不能获取到，最后再通过request.getRemoteAddr();获取
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
      ip = request.getRemoteAddr();
    }
    if (log.isDebugEnabled()) {
      Enumeration<String> headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        log.debug("header【{}】:{}", headerName, request.getHeader(headerName));
      }
    }
    return ip;
  }

  /**
   * 返回系统和浏览器信息
   *
   * @return
   */
  public static Map<String, Object> getOSInfo() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return getOSInfo(request);
  }

  /**
   * 获取请求参数
   *
   * @param request
   * @return
   */
  public static String getParam(HttpServletRequest request) {
    Enumeration enu = request.getParameterNames();
    String paraName = null;
    JSONObject object = new JSONObject();
    while (enu.hasMoreElements()) {
      paraName = enu.nextElement().toString();
      object.put(paraName, request.getParameter(paraName));
    }
    return object.toJSONString();
  }

  /**
   * 返回系统和浏览器信息
   *
   * @param request
   * @return
   */
  public static Map<String, Object> getOSInfo(HttpServletRequest request) {
    String agent = request.getHeader("User-Agent");
    // 解析agent字符串
    UserAgent userAgent = UserAgent.parseUserAgentString(agent);
    // Browser info
    StringBuffer browserInfo = new StringBuffer();
    Map<String, Object> map = new HashMap<>();
    if (userAgent != null) {
      // 获取浏览器对象
      Browser browser = userAgent.getBrowser();
      // 获取操作系统对象
      OperatingSystem operatingSystem = userAgent.getOperatingSystem();
      if (browser != null) {
        browserInfo.append(browser.getName()).append(":");// 浏览器名
        browserInfo.append(browser.getBrowserType()).append(":");// 浏览器类型
        browserInfo.append(browser.getGroup()).append(":");// 浏览器家族
        browserInfo.append(browser.getManufacturer()).append(":");// 浏览器生产厂商
        browserInfo.append(browser.getRenderingEngine()).append(":");// 浏览器使用的渲染引擎
      }
      browserInfo.append(userAgent.getBrowserVersion());// 浏览器版本
      if (operatingSystem != null) {
        browserInfo.append(operatingSystem.getName()).append(":");// 操作系统名
        map.put(OS_KEY, operatingSystem.getName());
        browserInfo.append(operatingSystem.getDeviceType()).append(":");// 访问设备类型
        browserInfo.append(operatingSystem.getGroup()).append(":");// 操作系统家族
        browserInfo.append(operatingSystem.getGroup()).append(":");// 操作系统生产厂商:
      }
    }
    map.put(BROWSER_KEY, browserInfo);
    return map;
  }
}
