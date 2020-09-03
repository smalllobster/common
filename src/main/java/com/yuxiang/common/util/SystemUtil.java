
package com.yuxiang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * 功能说明: 是否分库
 * 开发人员: kjp
 * 开发时间: 2018/11/28 <br>
 * 功能描述: 是否分库<br>
 */
@Slf4j
public class SystemUtil {

  private static boolean isSharding = false;
  private static boolean isBackWeb = false;

  public static final boolean isSharding() {
    return isSharding;
  }

  public static final boolean isBackWeb() {
    return isBackWeb;
  }

  public static final void init(ApplicationContext applicationContext) {
    isSharding = applicationContext.containsBean("sysCompanyService");
    isBackWeb = applicationContext.containsBean("sysUserService");
  }

}
