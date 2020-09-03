
package com.yuxiang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * 功能说明: 获取bean
 * 开发人员: kjp
 * 开发时间: 2018/6/28 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Component
@Slf4j
public class SpringUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext;
  private static DefaultListableBeanFactory beanFactory;

  /**
   * 获取applicationContext
   *
   * @return
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (SpringUtil.applicationContext == null) {
      SpringUtil.applicationContext = applicationContext;
      beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }
  }

  /**
   * 注册bean到Spring容器中
   *
   * @param obj
   */
  public static void register(Object obj) {
    beanFactory.registerSingleton(obj.getClass().getName(), obj);
  }

  /**
   * 注册bean到Spring容器中（提前删除）
   *
   * @param obj
   */
  public static void register(Object obj, String beanName, boolean remove) {
    if (remove) {
      beanFactory.destroySingleton(beanName);
    }
    beanFactory.registerSingleton(beanName, obj);
  }

  /**
   * 注册bean到Spring容器中
   *
   * @param obj
   */
  public static void register(Object obj, String beanName) {
    beanFactory.registerSingleton(beanName, obj);
  }

  /**
   * 通过name获取 Bean.
   *
   * @param name
   * @return
   */
  public static Object getBean(String name) {
    return getApplicationContext().getBean(name);
  }

  /**
   * 通过class获取Bean.
   *
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T getBean(Class<T> clazz) {
    return getApplicationContext().getBean(clazz);
  }

  /**
   * 通过name,以及Clazz返回指定的Bean
   *
   * @param name
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return getApplicationContext().getBean(name, clazz);
  }

  /**
   * 获取某类型的实例
   *
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> Map<String, T> getBeans(Class<T> clazz) {
    return getApplicationContext().getBeansOfType(clazz);
  }

  /**
   * 获取服务路径
   */
  public static String getServicePath() {
    String servicePath = null;
    try {
      File pathFile = new File(ResourceUtils.getURL("").getPath());
      servicePath = pathFile.getAbsolutePath();
    } catch (FileNotFoundException e) {
      log.error("获取服务路径错误");
    }
    return servicePath;
  }
}
